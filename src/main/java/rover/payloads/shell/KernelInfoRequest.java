package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = KernelInfoReply.class)
public class KernelInfoRequest implements Content {
}
