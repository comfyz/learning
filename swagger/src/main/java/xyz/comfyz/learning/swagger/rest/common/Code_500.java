package xyz.comfyz.learning.swagger.rest.common;

import org.springframework.http.HttpStatus;

/**
 * @author :    comfy
 * @date :      2018-05-08 09:41:38
 * @since :     1.8
 * <p>
 */
public enum Code_500 implements Code {
    /**
     * 调用第三方服务异常
     */
    THIRD_PARTY(5003, "Bad Request");

    private final int code;
    private final String message;

    Code_500(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
