package rover.payloads.io;

import rover.payloads.Content;
import rover.payloads.marker.IOPubPayload;

@IOPubPayload
public class StatusPayload implements Content {

    private String execution_state;

    public StatusPayload(String execution_state) {
        this.execution_state = execution_state;
    }

    public StatusPayload() {
    }

    public String getExecutionState() {
        return execution_state;
    }
}
