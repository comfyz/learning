package xyz.comfyz.learning.swagger.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.comfyz.rest.exceptions.EnableExceptionHandler;
import xyz.comfyz.rest.exceptions.handler.RestExceptionHandler;

/**
 * Author:      宗康飞
 * Mail:        zongkangfei@sudiyi.cn
 * Date:        18:40 2018/3/19
 * Version:     1.0
 * Description:
 */
@EnableExceptionHandler //启用自定义拦截
@RestControllerAdvice   //异常拦截
public class GlobalExceptionHandler extends RestExceptionHandler {
}
