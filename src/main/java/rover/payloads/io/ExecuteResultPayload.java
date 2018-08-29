package rover.payloads.io;

import rover.payloads.Content;
import rover.payloads.marker.IOPubPayload;

import java.util.Map;

@IOPubPayload
public class ExecuteResultPayload implements Content {

    private int execution_count;
    private Map<String, Object> data;
    private Map<String, Object> metadata;

    public ExecuteResultPayload(int execution_count, Map<String, Object> data, Map<String, Object> metadata) {
        this.execution_count = execution_count;
        this.data = data;
        this.metadata = metadata;
    }

    public ExecuteResultPayload() {
    }

    public int getExecutionCount() {
        return execution_count;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
