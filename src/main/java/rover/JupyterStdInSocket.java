package rover;

/**
 * "this ROUTER socket is connected to all frontends, and it allows the kernel to request input from the active
 * frontend when raw_input() is called. The frontend that executed the code has a DEALER socket that acts as a
 * ‘virtual keyboard’ for the kernel while this communication is happening (illustrated in the figure by the black
 * outline around the central keyboard). In practice, frontends may display such kernel requests using a special
 * input widget or otherwise indicating that the user is to type input for the kernel instead of normal commands in
 * the frontend.
 *
 * All messages are tagged with enough information (details below) for clients to know which messages come from their
 * own interaction with the kernel and which ones are from other clients, so they can display each type appropriately."
 */
public class JupyterStdInSocket extends JupyterSocket {

    public JupyterStdInSocket(String host, String port, byte uid) {
        super(uid, host, port, "stdin");
    }
}
