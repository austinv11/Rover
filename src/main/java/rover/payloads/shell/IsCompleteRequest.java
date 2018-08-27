package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = IsCompleteReply.class)
public class IsCompleteRequest implements Content {

    private String code;

    public IsCompleteRequest(String code) {
        this.code = code;
    }

    public IsCompleteRequest() {
    }

    public String getCode() {
        return code;
    }
}
