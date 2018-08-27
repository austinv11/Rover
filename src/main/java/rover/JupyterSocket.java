package rover;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import rover.payloads.Payload;
import rover.payloads.ContentTypes;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.Closeable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public abstract class JupyterSocket implements Closeable {

    private final byte[] uid;
    private final String delimiter = "<IDS|MSG>";
    private final Mac digest;
    private final Thread runner;
    private final Object writeLock = new Object();
    private final Map<Class<? extends Payload>, Collection<Consumer>> handlers
            = new ConcurrentHashMap<>();

    private final AtomicReference<Consumer<Payload>> messenger = new AtomicReference<>(null);
    private final ExecutorService worker = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(new ThreadGroup("Jupyter-Worker@" + JupyterSocket.this.hashCode()),
                r, "Jupyter-Worker-Thread@" + JupyterSocket.this.hashCode(), 0, false);
        t.setPriority(3);
        t.setDaemon(true);
        t.setUncaughtExceptionHandler((th, e) -> {
            System.err.println("Exception caught in thread " + th + ", ignoring!");
            System.err.println("Stacktrace Dump:");
            e.printStackTrace(System.err);
        });
        return t;
    });

    public JupyterSocket(byte[] uid, /*nullable*/ String key, String host, String port, String role) {
        this.uid = uid;
        if (key == null)
            digest = null;
        else {
            try {
                digest = Mac.getInstance("HmacSHA256");
                SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
                digest.init(secret_key);
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        }
        this.runner = new Thread(
                new ThreadGroup(Thread.currentThread().getThreadGroup(), "JupyterGroup"),
                () -> {
                    try (ZContext context = new ZContext(1)) {
                        ZMQ.Socket socket = context.createSocket(ZMQ.REP);
                        socket.bind("tcp://" + host +":" + port);

                        messenger.set(p -> {
                            synchronized (writeLock) {
                                byte[] header = p.headerBytes();
                                byte[] parent = p.parentBytes();
                                byte[] metadata = p.metadataBytes();
                                byte[] content = p.contentBytes();

                                byte[] sig = digest(header, parent, metadata, content);

                                socket.send(uid);
                                socket.send(delimiter);
                                socket.send(sig);
                                socket.send(header);
                                socket.send(parent);
                                socket.send(metadata);
                                socket.send(content);
                                socket.send(new byte[0]);
                            }
                        });

                        while (!Thread.currentThread().isInterrupted()) {
                            byte[] recUid = socket.recv();
                            if (Arrays.equals(uid, recUid)) {
                                byte[] delim = socket.recv();
                                byte[] sig = socket.recv();
                                byte[] header = socket.recv();
                                byte[] parent = socket.recv();
                                byte[] metadata = socket.recv();
                                byte[] content = socket.recv();
//                                byte[] additionalInfo = socket.recv(); Implicitly drop additional info

                                worker.execute(() -> {
                                    if (!delimiter.equals(new String(delim))) {
                                        System.err.println("Bad delimiter, dropping packet! Delimiter=" + Arrays.toString(delim));
                                        return;
                                    }

                                    final Payload p;
                                    try {
                                        p = ContentTypes.parse(header, parent, metadata, content);
                                    } catch (Exception e) {
                                        System.err.println("Exception encountered while parsing a payload!");
                                        e.printStackTrace();
                                        return;
                                    }
                                    //noinspection unchecked
                                    handlers.getOrDefault(p.getClass(), Collections.emptyList())
                                            .forEach(c -> c.accept(p));
                                });
                            }
                        }
                    }
                }, "Jupyter-"+ role +"-Socket@" + hashCode(), 0, false);
        runner.setDaemon(true);
        runner.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Exception caught in thread " + t + ", ignoring!");
            System.err.println("Stacktrace Dump:");
            e.printStackTrace(System.err);
        });
        runner.run();
    }

    public void sendPayload(Payload p) {
        while (messenger.get() == null)
            Thread.onSpinWait();

        messenger.get().accept(p);
    }

    public <T extends Payload> void handle(Class<T> payloadType, Consumer<T> callback) {
        handlers.computeIfAbsent(payloadType, c -> new CopyOnWriteArrayList<>()).add(callback);
    }

    public byte[] digest(byte[] header, byte[] parent, byte[] metadata, byte[] content) {
        if (digest == null)
            return new byte[0];

        byte[] hash;
        synchronized (digest) {
            digest.update(header);
            digest.update(parent);
            digest.update(metadata);
            digest.update(content);
            hash = digest.doFinal();
        }
        return hash;
    }

    @Override
    public void close() {
        runner.interrupt();
        runner.stop();
    }
}
