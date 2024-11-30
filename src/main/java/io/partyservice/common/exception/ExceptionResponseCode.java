package io.partyservice.common.exception;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionResponseCode {
    PARTY_NOT_FOUND(NOT_FOUND, "The party is not in DB partyId : %d");
    private final HttpStatus httpStatus;
    private final String message;
}
