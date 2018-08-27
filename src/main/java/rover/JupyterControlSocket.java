package rover;

/**
 * "This channel is identical to Shell, but operates on a separate socket, to allow important messages to avoid queueing
 * behind execution requests (e.g. shutdown or abort)."
 */
public class JupyterControlSocket extends JupyterSocket {

    public JupyterControlSocket(String host, String port, byte uid) {
        super(uid, host, port, "Control");
    }
}
