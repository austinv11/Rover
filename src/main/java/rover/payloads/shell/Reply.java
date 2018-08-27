package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

@ShellPayload
public abstract class Reply implements Content {

    private String status;

    //Status = error
    private String ename;
    private String evalue;
    private String[] traceback;

    public Reply(String status, String ename, String evalue, String[] traceback) {
        this.status = status;
        this.ename = ename;
        this.evalue = evalue;
        this.traceback = traceback;
    }

    public Reply(String status) {
        this.status = status;
    }

    public Reply() {}

    public boolean isOk() {
        return status.equalsIgnoreCase("ok");
    }

    public boolean isError() {
        return status.equalsIgnoreCase("error");
    }

    @Deprecated(since = "5.1", forRemoval = true)
    public boolean isAbort() {
        return status.equalsIgnoreCase("abort");
    }

    public String getStatus() {
        return status;
    }

    public String getEname() {
        return ename;
    }

    public String getEvalue() {
        return evalue;
    }

    public String[] getTraceback() {
        return traceback;
    }
}
