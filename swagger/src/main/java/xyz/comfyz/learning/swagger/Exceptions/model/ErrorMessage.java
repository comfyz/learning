package xyz.comfyz.learning.swagger.Exceptions.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

@ApiModel("错误消息")
public class ErrorMessage {

    @ApiModelProperty("错误码")
    private final int code;
    @ApiModelProperty("错误消息")
    private final String msg;

    public ErrorMessage(HttpStatus status) {
        this(status.value(), status.getReasonPhrase());
    }

    public ErrorMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
