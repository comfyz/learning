package xyz.comfyz.learning.swagger.rest.common;

import org.springframework.http.HttpStatus;

/**
 * @author :    comfy
 * @date :      2018-05-07 16:13:10
 * @since :     1.8
 * <p>
 * 此类异常都是请求不当造成的400错误
 */
public enum Code_400 implements Code {

    /**
     * 请求异常，包括参数，数据格式等
     */
    BAD_REQUEST(4000, "Bad Request"),

    /**
     * 找不到请求的数据
     */
    NOT_FOUND(4001, "Unsupported Operation"),

    /**
     * 不支持的 错误操作
     */
    UNSUPPORTED_OPERATION(4001, "Unsupported Operation");

    private final int code;
    private final String message;

    Code_400(int code, String message) {
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
