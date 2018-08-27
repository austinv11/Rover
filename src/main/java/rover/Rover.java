package rover;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * Simple asynchronous client for Jupyter. For synchronicity, one can use the internal {@link rover.MessengerImpl} but
 * this is not recommended!
 *
 * @see #message(java.util.function.Consumer)
 * @see <a href="https://jupyter-client.readthedocs.io/en/stable/messaging.html">Jupyter Protocol Docs</a>
 */
public final class Rover implements Closeable {

    private final ExecutionQueue queue = new ExecutionQueue(); //Gates sent packets to one at a time and does so asynchronously

    private final String host;
    private final String port;

    private volatile MessengerImpl messenger;

    private final Object lock = new Object();

    public Rover(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public Rover(String port) {
        this("*", port);
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public void start() {
        synchronized (lock) {
            if (messenger == null) {
                messenger = new MessengerImpl(this);
                queue.start();
            }
        }
    }

    public void message(Consumer<Messenger> messengerConsumer) {
        queue.addJob(() -> {
            final MessengerImpl messengerCopy = messenger;
            if (messengerCopy != null)
                messengerConsumer.accept(messengerCopy);
        });
    }

    @Override
    public void close() throws IOException {
        synchronized (lock) {
            messenger.close();
            messenger = null;
            queue.close();
        }
    }
}
