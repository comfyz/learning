package xyz.comfyz.learning.swagger.rest.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import xyz.comfyz.learning.swagger.rest.common.Code;
import xyz.comfyz.learning.swagger.rest.common.Code_200;

/**
 * @author :    comfy
 * @date :      2018-05-08 09:53:38
 * @since :     1.8
 * <p>
 */
@ApiModel("返回消息体")
public class Result<T> {

    @ApiModelProperty("状态码")
    private final int code;

    @ApiModelProperty("状态码解释")
    private final String msg;

    @ApiModelProperty("返回数据")
    private final T data;

    /**
     * 成功
     */
    public Result() {
        this(Code_200.SUCCESS);
    }

    /**
     * 成功
     *
     * @param data 返回数据
     */
    public Result(T data) {
        this(Code_200.SUCCESS, data);
    }

    /**
     * 自定义返回code
     *
     * @param code 返回状态码
     */
    public Result(Code code) {
        this(code, code.message(), null);
    }

    /**
     * 自定义返回code，状态码解释
     *
     * @param code 返回状态码
     * @param msg  状态码解释
     */
    public Result(Code code, String msg) {
        this(code, msg, null);
    }

    /**
     * 自定义返回code
     *
     * @param code 返回状态码
     * @param data 返回数据
     */
    public Result(Code code, T data) {
        this(code, code.message(), data);
    }

    /**
     * 自定义返回code，状态码解释，数据
     *
     * @param code 返回状态码
     * @param data 返回数据
     */
    public Result(Code code, String msg, T data) {
        this.code = code.code();
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
