package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = InspectReply.class)
public class InspectRequest implements Content {

    private String code;
    private int code_pos;
    private int detail_level; //0 or 1

    public InspectRequest(String code, int code_pos, int detail_level) {
        this.code = code;
        this.code_pos = code_pos;
        this.detail_level = detail_level;
    }

    public InspectRequest() {
    }

    public String getCode() {
        return code;
    }

    public int getCodePos() {
        return code_pos;
    }

    public int getDetailLevel() {
        return detail_level;
    }
}
