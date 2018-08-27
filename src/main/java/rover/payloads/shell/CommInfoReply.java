package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

import java.util.Map;

@ShellPayload(request = CommInfoRequest.class)
public class CommInfoReply extends Reply {

    private Map<String, CommInfoRequest> comms;

    public CommInfoReply(String status, String ename, String evalue, String[] traceback,
                         Map<String, CommInfoRequest> comms) {
        super(status, ename, evalue, traceback);
        this.comms = comms;
    }

    public CommInfoReply(String status, Map<String, CommInfoRequest> comms) {
        super(status);
        this.comms = comms;
    }

    public CommInfoReply() {
    }

    public Map<String, CommInfoRequest> getComms() {
        return comms;
    }
}
