package xyz.comfyz.learning.swagger.rest.common.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ErrorHandler;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ErrorMessage;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ResponseException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author :    comfy
 * @date :      2018-05-07 11:43:20
 * @since :     1.8
 * <p>
 * 全局异常处理
 */
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultHandlerException(Exception e) {
        ErrorMessage error = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage error = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Object> handlerResponseException(ResponseException e) {
        if (e instanceof ErrorHandler && ((ErrorHandler) e).getHandler() != null)
            EXECUTOR.execute(((ErrorHandler) e).getHandler());
        return new ResponseEntity<>(e.getErrorMessage(), new HttpHeaders(), e.getCode().status());
    }

}
