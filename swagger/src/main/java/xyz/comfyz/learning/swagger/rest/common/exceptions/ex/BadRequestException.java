package xyz.comfyz.learning.swagger.rest.common.exceptions.ex;

import xyz.comfyz.learning.swagger.rest.common.Code;
import xyz.comfyz.learning.swagger.rest.common.Code_400;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ResponseException;

/**
 * @author :    comfy
 * @date :      2018-05-07 15:51:31
 * @since :     1.8
 * <p>
 * 请求异常，包括参数，数据格式等
 */
public final class BadRequestException extends ResponseException {

    private static final Code BAD_REQUEST = Code_400.BAD_REQUEST;

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
