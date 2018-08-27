package rover.payloads;

import java.time.OffsetDateTime;

public class Header {

    private String msg_id;
    private String username;
    private String session;
    private OffsetDateTime date;
    private String msg_type;
    private String version;

    public Header(String msg_id, String username, String session, OffsetDateTime date, String msg_type,
                  String version) {

        this.msg_id = msg_id;
        this.username = username;
        this.session = session;
        this.date = date;
        this.msg_type = msg_type;
        this.version = version;
    }

    public Header() {

    }

    public String getMsgId() {
        return msg_id;
    }

    public String getUsername() {
        return username;
    }

    public String getSession() {
        return session;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public String getMsgType() {
        return msg_type;
    }

    public String getVersion() {
        return version;
    }
}
