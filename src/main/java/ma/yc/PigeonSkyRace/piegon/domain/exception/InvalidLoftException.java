package ma.yc.PigeonSkyRace.piegon.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidLoftException extends RuntimeException {
    public InvalidLoftException(String message) {
        super(message);
    }
}