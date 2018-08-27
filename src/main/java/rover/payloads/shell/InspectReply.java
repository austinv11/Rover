package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

import java.util.Map;

@ShellPayload(request = InspectRequest.class)
public class InspectReply extends Reply {

    private boolean found;
    private Map<String, Object> data;
    private Map<String, Object> metadata;

    public InspectReply(String status, String ename, String evalue, String[] traceback, boolean found, Map<String,
            Object> data, Map<String, Object> metadata) {
        super(status, ename, evalue, traceback);
        this.found = found;
        this.data = data;
        this.metadata = metadata;
    }

    public InspectReply(String status, boolean found, Map<String, Object> data, Map<String, Object> metadata) {
        super(status);
        this.found = found;
        this.data = data;
        this.metadata = metadata;
    }

    public InspectReply() {
    }

    public boolean isFound() {
        return found;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
