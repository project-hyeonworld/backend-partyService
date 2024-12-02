package io.partyservice.common.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.partyservice.api.party.event.PartyTerminateEvent;
import java.io.IOException;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
public class PartyTerminateEventDeserializer implements Deserializer<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PartyTerminateEvent deserialize(String s, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, PartyTerminateEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
