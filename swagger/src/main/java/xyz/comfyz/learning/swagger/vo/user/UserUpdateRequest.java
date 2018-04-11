package xyz.comfyz.learning.swagger.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import xyz.comfyz.learning.swagger.enums.Gender;

/**
 * Author:      宗康飞
 * Mail:        zongkangfei@sudiyi.cn
 * Date:        15:50 2018/3/19
 * Version:     1.0
 * Description:
 */
@Getter
@ApiModel("新增用户")
public class UserUpdateRequest {

    @ApiModelProperty(value = "姓名", dataType = "String", required = true)
    private final String name;

    @ApiModelProperty(value = "性别", dataType = "String", required = true)
    private final Gender gender;

    public UserUpdateRequest(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }
}
