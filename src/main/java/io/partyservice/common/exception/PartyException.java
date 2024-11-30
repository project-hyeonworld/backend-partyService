package io.partyservice.common.exception;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
public class PartyException extends ServerException {

    public PartyException(ExceptionResponseCode code) {
        this(code, "runtimeMessage is not available");
    }

    public PartyException(ExceptionResponseCode code, String runtimeMessage) {
        super(code, runtimeMessage);
    }
}
