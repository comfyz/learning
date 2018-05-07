package xyz.comfyz.learning.swagger.Exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import xyz.comfyz.learning.swagger.Exceptions.common.ResponseException;
import xyz.comfyz.learning.swagger.Exceptions.model.ErrorMessage;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author :    comfy
 * @date :      2018-05-07 11:43:20
 * @since :     1.8
 * <p>
 *
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerAllException(Exception e) {
        ErrorMessage error = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), e.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage error = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseException.class)
    private ResponseEntity<Object> handlerResponseException(ResponseException e) {
        return new ResponseEntity<>(e.getErrorMessage(), new HttpHeaders(), e.getCode().status());
    }
}
