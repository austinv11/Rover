package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

import java.util.List;

@ShellPayload(request = HistoryRequest.class)
public class HistoryReply extends Reply {

    private List<List<Object>> content; //Supposed to be a list of triples

    public HistoryReply(String status, String ename, String evalue, String[] traceback, List<List<Object>> content) {
        super(status, ename, evalue, traceback);
        this.content = content;
    }

    public HistoryReply(String status, List<List<Object>> content) {
        super(status);
        this.content = content;
    }

    public HistoryReply() {
    }

    public List<List<Object>> getContent() {
        return content;
    }
}
