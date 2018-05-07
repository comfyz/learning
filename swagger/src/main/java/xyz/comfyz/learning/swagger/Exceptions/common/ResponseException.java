package xyz.comfyz.learning.swagger.Exceptions.common;

import xyz.comfyz.learning.swagger.Exceptions.code.ErrorCode;
import xyz.comfyz.learning.swagger.Exceptions.model.ErrorMessage;

/**
 * @author :    comfy
 * @date :      2018-05-07 14:59:29
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class ResponseException extends RuntimeException {

    private final ErrorCode code;

    public ResponseException(ErrorCode code) {
        super(code.message());
        this.code = code;
    }

    public ResponseException(ErrorCode code, String msg) {
        super(msg);
        this.code = code;
    }

    public ResponseException(ErrorCode code, String msg, Throwable e) {
        super(code.message(), e);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public ErrorMessage getErrorMessage() {
        return new ErrorMessage(code.code(), getMessage());
    }
}
