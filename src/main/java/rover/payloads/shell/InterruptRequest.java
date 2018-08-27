package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = InterruptReply.class)
public class InterruptRequest implements Content {
}
