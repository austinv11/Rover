package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

import java.util.List;
import java.util.Map;

@ShellPayload(request = ExecuteRequest.class)
public class ExecuteReply extends Reply {

    private int execution_count;

    @Deprecated //https://jupyter-client.readthedocs.io/en/stable/messaging.html#payloads-deprecated
    private List<Map<String, Object>> payload;

    private Map<String, Object> user_expressions;

    public ExecuteReply(String status, String ename, String evalue, String[] traceback, int execution_count) {
        super(status, ename, evalue, traceback);
        this.execution_count = execution_count;
    }

    public ExecuteReply(String status, int execution_count, List<Map<String, Object>> payload,
                        Map<String, Object> user_expressions) {
        super(status);
        this.execution_count = execution_count;
        this.payload = payload;
        this.user_expressions = user_expressions;
    }

    public ExecuteReply() {
    }

    public int getExecutionCount() {
        return execution_count;
    }

    public List<Map<String, Object>> getPayload() {
        return payload;
    }

    public Map<String, Object> getUserExpressions() {
        return user_expressions;
    }
}
