package xyz.comfyz.learning.swagger.rest.common;

import org.springframework.http.HttpStatus;

/**
 * @author :    comfy
 * @date :      2018-05-08 09:57:20
 * @since :     1.8
 * <p>
 * 成功返回
 */
public enum Code_200 implements Code {

    /**
     * 请求异常，包括参数，数据格式等
     */
    SUCCESS(0, "ok");

    private final int code;
    private final String message;

    Code_200(int code, String message) {
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
