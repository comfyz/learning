package xyz.comfyz.learning.swagger.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import xyz.comfyz.learning.swagger.enums.Gender;

/**
 * Author:      宗康飞
 * Mail:        zongkangfei@sudiyi.cn
 * Date:        15:14 2018/3/19
 * Version:     1.0
 * Description:
 */
@ApiModel("用户")
@Getter
public class User {

    @ApiModelProperty(value = "用户ID", dataType = "Integer")
    private Integer id;
    @ApiModelProperty(value = "名称", dataType = "String")
    private String name;
    @ApiModelProperty(value = "性别", dataType = "String")
    private Gender gender;

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
