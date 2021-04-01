package zup.orange_talents.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
    
    private MessageSource _messageSource;
    
    public ValidationErrorHandler(MessageSource messageSource) {
        this._messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationErrorDto> handle(MethodArgumentNotValidException e){ 
        List<FieldError>fieldErrors = e.getBindingResult().getFieldErrors();
        List<ValidationErrorDto> errorList = new ArrayList<>();

        fieldErrors.forEach( error -> {
            String message = _messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ValidationErrorDto exception = new ValidationErrorDto(error.getField(), message);
            errorList.add(exception);
        });

        return errorList;
    }
}
