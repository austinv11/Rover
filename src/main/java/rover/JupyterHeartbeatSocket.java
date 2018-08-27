package rover;

/**
 * "This socket allows for simple bytestring messages to be sent between the frontend and the kernel to ensure that
 * they are still connected."
 */
public class JupyterHeartbeatSocket extends JupyterSocket {

    public JupyterHeartbeatSocket(String host, String port, byte uid) {
        super(uid, host, port, "Heartbeat");
    }
}
