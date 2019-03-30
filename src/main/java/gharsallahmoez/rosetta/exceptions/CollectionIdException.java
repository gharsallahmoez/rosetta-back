package gharsallahmoez.rosetta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CollectionIdException extends RuntimeException {
    public CollectionIdException(String message) {
        super(message);
    }

}
