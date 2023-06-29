package co.com.bancolombia.api.exceptions;

import co.com.bancolombia.model.exceptions.BusinessException;
import co.com.bancolombia.model.exceptions.TechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<ErrorResponseHTTP> handleBusinessException(BusinessException ex, WebRequest request) {
        log.error("Business exception", ex);

        var response = ErrorResponseHTTP.builder()
                .code(ex.getErrorMessage().getCode())
                .mensaje(ex.getErrorMessage().getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { TechnicalException.class })
    protected ResponseEntity<ErrorResponseHTTP> handleTechnicalException(TechnicalException ex, WebRequest request) {
        log.error("Technical exception", ex);

        var response = ErrorResponseHTTP.builder()
                .code(ex.getErrorMessage().getCode())
                .mensaje(ex.getErrorMessage().getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ErrorResponseHTTP> handleError(Exception ex, WebRequest request) {
        log.error("Internal error", ex);

        var response = ErrorResponseHTTP.builder()
                .code("500")
                .mensaje("Internal error").build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
