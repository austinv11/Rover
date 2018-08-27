package rover;


import java.io.Closeable;
import java.io.IOException;

/**
 * Connection and message manager. Interaction with this directly is possible, but not recommended!
 *
 * @see rover.Rover
 */
public class MessengerImpl implements Messenger, Closeable {

    private final Rover rover;

    //This bad boy can fit so many sockets...
    private final JupyterShellSocket shell;
    private final JupyterIOPubSocket iopub;
    private final JupyterStdInSocket stdin;
    private final JupyterControlSocket control;
    private final JupyterHeartbeatSocket heartbeat;

    protected MessengerImpl(Rover rover) {
        this.rover = rover;

        shell = new JupyterShellSocket(rover.getHost(), rover.getPort(), uid);
        iopub = new JupyterIOPubSocket(rover.getHost(), rover.getPort(), uid);
        stdin = new JupyterStdInSocket(rover.getHost(), rover.getPort(), uid);
        control = new JupyterControlSocket(rover.getHost(), rover.getPort(), uid);
        heartbeat = new JupyterHeartbeatSocket(rover.getHost(), rover.getPort(), uid);
    }

    private void exceptionally(Runnable r) {
        try {
            r.run();
        } catch (Throwable t) {}
    }

    @Override
    public void close() throws IOException {
        exceptionally(control::close); // This should be first to sever the connection
        exceptionally(shell::close);
        exceptionally(iopub::close);
        exceptionally(stdin::close);
        exceptionally(heartbeat::close);
    }
}
