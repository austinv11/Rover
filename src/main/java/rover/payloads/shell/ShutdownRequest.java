package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = ShutdownReply.class)
public class ShutdownRequest implements Content {

    private boolean restart;

    public ShutdownRequest(boolean restart) {
        this.restart = restart;
    }

    public ShutdownRequest() {
    }

    public boolean shouldRestart() {
        return restart;
    }
}
