package xyz.comfyz.learning.swagger.rest.common.exceptions;

import xyz.comfyz.learning.swagger.rest.common.Code;

/**
 * @author :    comfy
 * @date :      2018-05-07 14:59:29
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public abstract class ResponseException extends RuntimeException {

    private final Code code;

    public ResponseException(Code code) {
        super(code.message());
        this.code = code;
    }

    public ResponseException(Code code, String msg) {
        super(msg);
        this.code = code;
    }

    public ResponseException(Code code, String msg, Throwable e) {
        super(code.message(), e);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public ErrorMessage getErrorMessage() {
        return new ErrorMessage(code.code(), getMessage());
    }
}
