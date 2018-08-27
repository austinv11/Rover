package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = CommInfoReply.class)
public class CommInfoRequest implements Content {

    private String target_name;

    public CommInfoRequest(String target_name) {
        this.target_name = target_name;
    }

    public CommInfoRequest() {
    }

    public String getTargetName() {
        return target_name;
    }
}
