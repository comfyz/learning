package xyz.comfyz.learning.swagger.rest.common.exceptions.ex;

import xyz.comfyz.learning.swagger.rest.common.Code;
import xyz.comfyz.learning.swagger.rest.common.Code_500;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ErrorHandler;
import xyz.comfyz.learning.swagger.rest.common.exceptions.ResponseException;

/**
 * @author :    comfy
 * @date :      2018-05-08 09:40:25
 * @since :     1.8
 * <p>
 */
public class ThirdPartyException extends ResponseException implements ErrorHandler {
    private static final Code THIRD_PARTY = Code_500.THIRD_PARTY;

    public ThirdPartyException() {
        super(THIRD_PARTY);
    }

    public ThirdPartyException(String msg) {
        super(THIRD_PARTY, msg);
    }

    public ThirdPartyException(String msg, Throwable e) {
        super(THIRD_PARTY, msg, e);
    }

    @Override
    public Runnable getHandler() {
        return () -> System.out.println("我发送了邮件报告了该异常");
    }
}
