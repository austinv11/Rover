package rover.payloads.io;

import rover.payloads.Content;
import rover.payloads.marker.IOPubPayload;

@IOPubPayload
public class ErrorPayload implements Content {

    private int execution_count;

    public ErrorPayload(int execution_count) {
        this.execution_count = execution_count;
    }

    public ErrorPayload() {
    }

    public int getExecution_count() {
        return execution_count;
    }
}
