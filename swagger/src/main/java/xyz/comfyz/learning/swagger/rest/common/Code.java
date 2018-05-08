package xyz.comfyz.learning.swagger.rest.common;

import org.springframework.http.HttpStatus;

/**
 * @author :    comfy
 * @date :      2018-05-07 16:06:31
 * @since :     1.8
 * <p>
 * 错误码，code格式应该前三位为httpstatus，后面为自定义码，比如400类异常 4001 4002等
 */
public interface Code {

    /**
     * 获取 http状态码
     */
    HttpStatus status();

    /**
     * 返回自定义的状态码
     */
    int code();

    /**
     * 错误码说明
     */
    String message();
}
