package rover.payloads.io;

import rover.payloads.Content;
import rover.payloads.marker.IOPubPayload;

@IOPubPayload
public class StreamPayload implements Content {

    private String name;
    private String text;

    public StreamPayload(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public StreamPayload() {
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
