package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

import java.util.List;
import java.util.Map;

@ShellPayload(request = CompleteRequest.class)
public class CompleteReply extends Reply {

    private List<String> matches;
    private int cursor_start;
    private int cursor_end;
    private Map<String, Object> metadata;

    public CompleteReply(String status, String ename, String evalue, String[] traceback, List<String> matches,
                         int cursor_start, int cursor_end, Map<String, Object> metadata) {
        super(status, ename, evalue, traceback);
        this.matches = matches;
        this.cursor_start = cursor_start;
        this.cursor_end = cursor_end;
        this.metadata = metadata;
    }

    public CompleteReply(String status, List<String> matches, int cursor_start, int cursor_end,
                         Map<String, Object> metadata) {
        super(status);
        this.matches = matches;
        this.cursor_start = cursor_start;
        this.cursor_end = cursor_end;
        this.metadata = metadata;
    }

    public CompleteReply() {
    }

    public List<String> getMatches() {
        return matches;
    }

    public int getCursorStart() {
        return cursor_start;
    }

    public int getCursorEnd() {
        return cursor_end;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
