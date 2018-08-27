package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

@ShellPayload(request = ShutdownRequest.class)
public class ShutdownReply extends Reply {

    private boolean restart;

    public ShutdownReply(String status, String ename, String evalue, String[] traceback, boolean restart) {
        super(status, ename, evalue, traceback);
        this.restart = restart;
    }

    public ShutdownReply(String status, boolean restart) {
        super(status);
        this.restart = restart;
    }

    public ShutdownReply() {
    }

    public boolean shouldRestart() {
        return restart;
    }
}
