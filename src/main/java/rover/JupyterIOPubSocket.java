package rover;

/**
 * "this socket is the ‘broadcast channel’ where the kernel publishes all side effects (stdout, stderr, etc.) as well as
 * the requests coming from any client over the shell socket and its own requests on the stdin socket. There are a
 * number of actions in Python which generate side effects: print() writes to sys.stdout, errors generate tracebacks,
 * etc. Additionally, in a multi-client scenario, we want all frontends to be able to know what each other has sent to
 * the kernel (this can be useful in collaborative scenarios, for example). This socket allows both side effects and the
 * information about communications taking place with one client over the shell channel to be made available to all
 * clients in a uniform manner."
 */
public class JupyterIOPubSocket extends JupyterSocket {

    public JupyterIOPubSocket(String host, String port, byte uid) {
        super(uid, host, port, "IOPub");
    }
}
