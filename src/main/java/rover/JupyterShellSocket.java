package rover;

/**
 * "this single ROUTER socket allows multiple incoming connections from frontends, and this is the socket where requests
 * for code execution, object information, prompts, etc. are made to the kernel by any frontend. The communication on
 * this socket is a sequence of request/reply actions from each frontend and the kernel."
 */
public class JupyterShellSocket extends JupyterSocket {

    public JupyterShellSocket(String host, String port, byte uid) {
        super(uid, host, port, "Shell");
    }
}
