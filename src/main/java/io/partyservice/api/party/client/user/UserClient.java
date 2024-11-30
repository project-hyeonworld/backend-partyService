package io.partyservice.api.party.client.user;

import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
@FeignClient(name = "user-service", url = "{application.config.user-service-url}")
public interface UserClient {

    @GetMapping("/names")
    namesResponse getNamesByIds(Set<Long> ids);
}
