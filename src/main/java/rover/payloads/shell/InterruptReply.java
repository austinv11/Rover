package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

@ShellPayload(request = InterruptRequest.class)
public class InterruptReply extends Reply {

    public InterruptReply(String status, String ename, String evalue, String[] traceback) {
        super(status, ename, evalue, traceback);
    }

    public InterruptReply(String status) {
        super(status);
    }

    public InterruptReply() {
    }
}
