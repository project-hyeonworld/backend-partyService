package io.partyservice.api.party.client.user;

import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public record namesResponse(
        Map<Long, String> userIdNames
) {

}
