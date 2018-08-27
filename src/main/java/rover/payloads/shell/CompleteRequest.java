package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = CompleteReply.class)
public class CompleteRequest implements Content {

    private String code;
    private int cursor_pos;

    public CompleteRequest(String code, int cursor_pos) {
        this.code = code;
        this.cursor_pos = cursor_pos;
    }

    public CompleteRequest() {
    }

    public String getCode() {
        return code;
    }

    public int getCursorPos() {
        return cursor_pos;
    }
}
