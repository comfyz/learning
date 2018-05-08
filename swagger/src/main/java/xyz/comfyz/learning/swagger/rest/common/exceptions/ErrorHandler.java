package xyz.comfyz.learning.swagger.rest.common.exceptions;

/**
 * @author :    comfy
 * @date :      2018-05-08 09:25:51
 * @since :     1.8
 * <p>
 */
public interface ErrorHandler {
    Runnable getHandler();
}
