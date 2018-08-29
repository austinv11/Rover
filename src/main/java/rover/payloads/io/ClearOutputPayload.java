package rover.payloads.io;

import rover.payloads.Content;
import rover.payloads.marker.IOPubPayload;

@IOPubPayload
public class ClearOutputPayload implements Content {

    private boolean wait;

    public ClearOutputPayload(boolean wait) {
        this.wait = wait;
    }

    public ClearOutputPayload() {
    }

    public boolean isWait() {
        return wait;
    }
}
