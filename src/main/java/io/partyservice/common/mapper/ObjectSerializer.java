package io.partyservice.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;


/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public class ObjectSerializer implements Serializer<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, Object data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
