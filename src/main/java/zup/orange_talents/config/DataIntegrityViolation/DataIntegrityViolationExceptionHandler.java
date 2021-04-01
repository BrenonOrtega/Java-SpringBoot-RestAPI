package zup.orange_talents.config.DataIntegrityViolation;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataIntegrityViolationExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler( DataIntegrityViolationException.class )
    public String handle(DataIntegrityViolationException e){
        return e.getMostSpecificCause().getMessage();
    }
    
}
