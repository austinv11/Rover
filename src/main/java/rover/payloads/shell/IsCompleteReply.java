package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

@ShellPayload(request = IsCompleteRequest.class)
public class IsCompleteReply extends Reply {

    private String indent;

    public IsCompleteReply(String status, String ename, String evalue, String[] traceback) {
        super(status, ename, evalue, traceback);
    }

    public IsCompleteReply(String status) {
        super(status);
    }

    public IsCompleteReply(String status, String ename, String evalue, String[] traceback, String indent) {
        super(status, ename, evalue, traceback);
        this.indent = indent;
    }

    public IsCompleteReply(String status, String indent) {
        super(status);
        this.indent = indent;
    }

    public IsCompleteReply() {
    }

    public String getIndent() {
        return indent;
    }
}
