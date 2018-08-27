package rover.payloads.shell;

import rover.payloads.marker.ShellPayload;

import java.util.List;
import java.util.Map;

@ShellPayload(request = KernelInfoRequest.class)
public class KernelInfoReply extends Reply {

    private String protocol_version;
    private String implementation;
    private String implementation_version;
    private LanguageInfo language_info;
    private String banner;
    private List<Map<String, String>> help_links;

    public KernelInfoReply(String status, String ename, String evalue, String[] traceback, String protocol_version,
                           String implementation, String implementation_version, LanguageInfo language_info,
                           String banner, List<Map<String, String>> help_links) {
        super(status, ename, evalue, traceback);
        this.protocol_version = protocol_version;
        this.implementation = implementation;
        this.implementation_version = implementation_version;
        this.language_info = language_info;
        this.banner = banner;
        this.help_links = help_links;
    }

    public KernelInfoReply(String status, String protocol_version, String implementation,
                           String implementation_version, LanguageInfo language_info, String banner, List<Map<String,
            String>> help_links) {
        super(status);
        this.protocol_version = protocol_version;
        this.implementation = implementation;
        this.implementation_version = implementation_version;
        this.language_info = language_info;
        this.banner = banner;
        this.help_links = help_links;
    }

    public KernelInfoReply() {
    }

    public String getProtocolVersion() {
        return protocol_version;
    }

    public String getImplementation() {
        return implementation;
    }

    public String getImplementationVersion() {
        return implementation_version;
    }

    public LanguageInfo getLanguageInfo() {
        return language_info;
    }

    public String getBanner() {
        return banner;
    }

    public List<Map<String, String>> getHelpLinks() {
        return help_links;
    }

    public static class LanguageInfo {

        private String name;
        private String version;
        private String mimetype;
        private String file_extension;
        private String pygments_lexer;
        private Object codemirror_mode;
        private String nbconvert_exporter;

        public LanguageInfo(String name, String version, String mimetype, String file_extension,
                            String pygments_lexer, Object codemirror_mode, String nbconvert_exporter) {
            this.name = name;
            this.version = version;
            this.mimetype = mimetype;
            this.file_extension = file_extension;
            this.pygments_lexer = pygments_lexer;
            this.codemirror_mode = codemirror_mode;
            this.nbconvert_exporter = nbconvert_exporter;
        }

        public LanguageInfo() {
        }

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        public String getMimetype() {
            return mimetype;
        }

        public String getFileExtension() {
            return file_extension;
        }

        public String getPygmentsLexer() {
            return pygments_lexer;
        }

        public Object getCodemirrorMode() {
            return codemirror_mode;
        }

        public String getNbconvertExporter() {
            return nbconvert_exporter;
        }
    }
}
