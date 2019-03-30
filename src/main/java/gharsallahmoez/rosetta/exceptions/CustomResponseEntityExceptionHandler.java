package gharsallahmoez.rosetta.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<Object> handleCollectionIdException(CollectionIdException ex, WebRequest request){
        CollectionIdExceptionResponse exceptionResponse = new CollectionIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public final ResponseEntity<Object> handleCollectionNotFoundException(CollectionNotFoundException ex, WebRequest request){
        CollectionNotFoundExceptionResponse exceptionResponse = new CollectionNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse , HttpStatus.BAD_REQUEST);
    }

}
