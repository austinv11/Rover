package rover.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public enum ContentTypes {
    ;

    protected static final ObjectMapper mapper = new ObjectMapper().registerModules(new AfterburnerModule(),
            new Jdk8Module(), new JavaTimeModule());

    private final Class<? extends Content> type;

    ContentTypes(Class<? extends Content> type) {
        this.type = type;
    }

    public Class<? extends Content> getType() {
        return type;
    }

    public static Class<? extends Content> findType(String type) {
        return ContentTypes.valueOf(type.toUpperCase()).getType();
    }

    public static Payload parse(byte[] _header, byte[] _parent, byte[] _metadata, byte[] _content) throws IOException {
        Header header = mapper.readValue(_header, Header.class);
        Header parent = _parent == null || _parent.length == 0 ? null : mapper.readValue(_parent, Header.class);
        Map<String, Object> metadata = mapper.readValue(_metadata, TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Object.class));
        Content content = mapper.readValue(_content, findType(header.getMsgType()));
        return new Payload(header, parent, metadata, content);
    }
}
