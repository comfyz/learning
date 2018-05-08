package xyz.comfyz.learning.swagger.rest.common.exceptions.ex;

import xyz.comfyz.learning.swagger.rest.common.Code;
import xyz.comfyz.learning.swagger.rest.common.Code_400;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ResponseException;

/**
 * @author :    comfy
 * @date :      2018-05-08 09:14:30
 * @since :     1.8
 * <p>
 * 找不到请求的数据
 */
public final class NotFoundException extends ResponseException {

    private static final Code NOT_FOUND = Code_400.NOT_FOUND;

    public NotFoundException() {
        super(NOT_FOUND);
    }

    public NotFoundException(String msg) {
        super(NOT_FOUND, msg);
    }

    public NotFoundException(String msg, Throwable e) {
        super(NOT_FOUND, msg, e);
    }

}
