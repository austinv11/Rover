package rover.payloads.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import rover.payloads.Content;
import rover.payloads.marker.IOPubPayload;

import java.util.Map;

@IOPubPayload
public class DisplayDataPayload implements Content {

    private Map<String, Object> data;
    private Map<String, Object> metadata;
    @JsonProperty("transient")
    private Map<String, Object> _transient;

    public DisplayDataPayload(Map<String, Object> data, Map<String, Object> metadata, Map<String, Object> _transient) {
        this.data = data;
        this.metadata = metadata;
        this._transient = _transient;
    }

    public DisplayDataPayload() {
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public Map<String, Object> getTransient() {
        return _transient;
    }
}
