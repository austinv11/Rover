package rover.payloads;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class Payload {

    private final Header header, parent;
    private final Map<String, Object> metadata;
    private final Content content;

    public Payload(Header header, Header parent, Map<String, Object> metadata, Content content) {
        this.header = header;
        this.parent = parent;
        this.metadata = metadata;
        this.content = content;
    }

    public String type() {
        return header.getMsgType();
    }

    public Header header() {
        return header;
    }

    public Header parent() {
        return parent;
    }

    public Map<String, Object> metadata() {
        return metadata;
    }

    public Content content() {
        return content;
    }

    public byte[] headerBytes() {
        try {
            return ContentTypes.mapper.writeValueAsBytes(header);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] parentBytes() {
        try {
            return parent == null ? new byte[0] : ContentTypes.mapper.writeValueAsBytes(parent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] metadataBytes() {
        try {
            return ContentTypes.mapper.writeValueAsBytes(metadata);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] contentBytes() {
        try {
            return ContentTypes.mapper.writeValueAsBytes(content);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
