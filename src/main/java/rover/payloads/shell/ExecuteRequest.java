package rover.payloads.shell;

import rover.payloads.Content;
import rover.payloads.marker.ShellPayload;

import java.util.Map;

@ShellPayload(reply = ExecuteReply.class)
public class ExecuteRequest implements Content {

    private String code;
    private boolean silent;
    private boolean store_history;
    private Map<String, Object> user_expressions;
    private boolean allow_stdin;
    private boolean stop_on_error;

    public ExecuteRequest(String code, boolean silent, boolean store_history, Map<String, Object> user_expressions,
                          boolean allow_stdin, boolean stop_on_error) {
        this.code = code;
        this.silent = silent;
        this.store_history = store_history;
        this.user_expressions = user_expressions;
        this.allow_stdin = allow_stdin;
        this.stop_on_error = stop_on_error;
    }

    public ExecuteRequest() {
    }

    public String getCode() {
        return code;
    }

    public boolean isSilent() {
        return silent;
    }

    public boolean storesHistory() {
        return store_history;
    }

    public Map<String, Object> getUserExpressions() {
        return user_expressions;
    }

    public boolean allowsStdin() {
        return allow_stdin;
    }

    public boolean stopsOnError() {
        return stop_on_error;
    }
}
