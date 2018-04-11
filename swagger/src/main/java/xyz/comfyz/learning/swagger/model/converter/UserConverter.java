package xyz.comfyz.learning.swagger.model.converter;

import org.springframework.beans.BeanUtils;
import xyz.comfyz.learning.swagger.model.User;
import xyz.comfyz.learning.swagger.vo.user.UserUpdateRequest;

/**
 * Author:      宗康飞
 * Mail:        zongkangfei@sudiyi.cn
 * Date:        15:48 2018/3/19
 * Version:     1.0
 * Description:
 */
public class UserConverter {

    public static User convert(UserUpdateRequest request){
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }
}
