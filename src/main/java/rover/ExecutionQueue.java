package rover;

import java.io.Closeable;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutionQueue implements Closeable {

    private final AtomicInteger signal = new AtomicInteger(0);
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private final Thread thread;

    public ExecutionQueue() {
        thread = new Thread(
                new ThreadGroup(Thread.currentThread().getThreadGroup(), "ExecutionQueueGroup@" + this.hashCode()),
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        while (signal.get() == 0) {
                            Thread.onSpinWait();
                        }
                        try {
                            queue.poll().run();
                        } catch (Throwable t) {
                            throw t;
                        } finally {
                            signal.decrementAndGet();
                        }
                    }
                }, "ExecutionQueue@" + this.hashCode(),
                0, false);
        thread.setDaemon(true);
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Exception caught in thread " + t + ", ignoring!");
            System.err.println("Stacktrace Dump:");
            e.printStackTrace(System.err);
        });
        thread.setPriority(3); //Lower priority a bit
    }

    public void start() {
        thread.start();
    }

    public void restart() {
        start();
    }

    public void addJob(Runnable r) {
        if (r == null) throw new NullPointerException();

        queue.add(r);
        signal.incrementAndGet();
    }

    @Override
    public synchronized void close() throws IOException {
        queue.clear();
        signal.set(0);
        thread.interrupt();
        thread.stop();
    }
}
