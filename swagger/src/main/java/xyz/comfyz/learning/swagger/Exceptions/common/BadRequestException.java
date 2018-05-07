package xyz.comfyz.learning.swagger.Exceptions.common;

import xyz.comfyz.learning.swagger.Exceptions.code.ErrorCode_400;
import xyz.comfyz.learning.swagger.Exceptions.code.ErrorCode;

/**
 * @author :    comfy
 * @date :      2018-05-07 15:51:31
 * @since :     1.8
 * <p>
 *
 * </p>
 */
public class BadRequestException extends ResponseException {

    private static final ErrorCode BAD_REQUEST = ErrorCode_400.BAD_REQUEST;

    public BadRequestException() {
        super(BAD_REQUEST);
    }

    public BadRequestException(String msg) {
        super(BAD_REQUEST, msg);
    }

    public BadRequestException(String msg, Throwable e) {
        super(BAD_REQUEST, msg, e);
    }
}
