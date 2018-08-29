package rover.payloads.io;

import rover.payloads.Content;
import rover.payloads.marker.IOPubPayload;

@IOPubPayload
public class CodeInputPayload implements Content {

    private String code;
    private int execution_count;

    public CodeInputPayload(String code, int execution_count) {
        this.code = code;
        this.execution_count = execution_count;
    }

    public CodeInputPayload() {
    }

    public String getCode() {
        return code;
    }

    public int getExecutionCount() {
        return execution_count;
    }
}
