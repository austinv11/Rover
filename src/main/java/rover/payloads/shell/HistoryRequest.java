package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload(reply = HistoryReply.class)
public class HistoryRequest implements Content {

    private boolean output;
    private boolean raw;
    private String hist_access_type;
    private int session;
    private int start;
    private int stop;
    private int n;
    private String pattern;
    private int unique;

    public HistoryRequest(boolean output, boolean raw, String hist_access_type, int session, int start, int stop,
                          int n, String pattern, int unique) {
        this.output = output;
        this.raw = raw;
        this.hist_access_type = hist_access_type;
        this.session = session;
        this.start = start;
        this.stop = stop;
        this.n = n;
        this.pattern = pattern;
        this.unique = unique;
    }

    public HistoryRequest() {
    }

    public boolean isOutput() {
        return output;
    }

    public boolean isRaw() {
        return raw;
    }

    public String getHistAccessType() {
        return hist_access_type;
    }

    public int getSession() {
        return session;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    public int getN() {
        return n;
    }

    public String getPattern() {
        return pattern;
    }

    public int getUnique() {
        return unique;
    }
}
