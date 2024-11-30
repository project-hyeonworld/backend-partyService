package io.partyservice.api.party.controller;


import io.partyservice.api.party.application.PartyFacade;
import io.partyservice.api.party.application.PartyScoreFacade;
import io.partyservice.api.party.controller.dto.req.PartyBeginRequest;
import io.partyservice.api.party.controller.dto.res.PartyBeginResponse;
import io.partyservice.api.party.controller.dto.res.PartyTerminateResponse;
import io.partyservice.api.party.controller.dto.res.RankingResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/parties")
public class PartyController {

    private final PartyFacade partyFacade;
    private final PartyScoreFacade partyScoreFacade;

    @PostMapping
    public ResponseEntity<PartyBeginResponse> beginParty(@RequestBody PartyBeginRequest request) {
        return ResponseEntity.ok(PartyBeginResponse.from(partyFacade.begin(request.toCommand())));
    }

    @PatchMapping("/{partyId}")
    public ResponseEntity<PartyTerminateResponse> terminateParty(
            @PathVariable long partyId
    ) {
        return ResponseEntity.ok(PartyTerminateResponse.from(partyFacade.terminate(partyId)));
    }

    @GetMapping("/{partyId}/rankings")
    public ResponseEntity<RankingResponse> ranking(
            @PathVariable long partyId
    ) {
        return ResponseEntity.ok(RankingResponse.from(partyScoreFacade.createRankingTable(partyId)));
    }

}
