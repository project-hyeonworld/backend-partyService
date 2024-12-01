package io.partyservice.api.party.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */

@FeignClient(name = "user-service", url = "${my.feign-client.user-service.address}"+":${my.feign-client.user-service.port}"+"${my.feign-client.user-service.end-point}")
public interface UserClient {

    @GetMapping("/{userId}/name")
    String getNameById(@PathVariable("userId") long userId);
}