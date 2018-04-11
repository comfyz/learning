package xyz.comfyz.learning.swagger.controller;

import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import xyz.comfyz.learning.swagger.enums.Gender;
import xyz.comfyz.learning.swagger.model.User;
import xyz.comfyz.learning.swagger.model.converter.UserConverter;
import xyz.comfyz.learning.swagger.vo.user.UserUpdateRequest;
import xyz.comfyz.rest.exceptions.exception.BadRequestException;
import xyz.comfyz.rest.exceptions.handler.ErrorMessage;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:      宗康飞
 * Mail:        zongkangfei@sudiyi.cn
 * Date:        15:15 2018/3/19
 * Version:     1.0
 * Description:
 */
@Api(tags = {"用户管理", "用户系统"}, description = "包含对用户的CURD等操作")
@ApiResponses({@ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorMessage.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessage.class)})
@RestController
@RequestMapping("user2")
public class UserController2 {

    private static Map<Integer, User> userCache = new ConcurrentHashMap<>();
    private static AtomicInteger lastId = new AtomicInteger(1);

    static {
        int id = lastId.getAndIncrement();
        userCache.put(id, new User().setId(id).setName("张三").setGender(Gender.MALE));
        id = lastId.getAndIncrement();
        userCache.put(id, new User().setId(id).setName("李四").setGender(Gender.FEMALE));
        id = lastId.getAndIncrement();
        userCache.put(id, new User().setId(id).setName("王五").setGender(Gender.MALE));
        id = lastId.getAndIncrement();
        userCache.put(id, new User().setId(id).setName("赵六").setGender(Gender.FEMALE));
    }

    /**
     * paramType 查询参数类型
     * - path    以地址的形式提交数据
     * - query   直接跟参数完成自动映射赋值
     * - body    以流的形式提交 仅支持POST
     * - header  参数在request headers 里边提交
     * - form    以form表单的形式提交 仅支持POST
     * <p>
     * dataType
     * - Long
     * - String
     */
    @ApiOperation("获取用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "Long")
    @GetMapping("{id}")
    public User find(@PathVariable Integer id) {
        if (id == null || id <= 0)
            throw new BadRequestException("id is not present");
        return userCache.get(id);
    }

    @ApiOperation("获取所有用户")
    @GetMapping("all")
    public Collection<User> findAll() {
        return userCache.values();
    }

    @ApiOperation("新增用户")
    @PostMapping
    public User add(UserUpdateRequest request) {
        User newUser = UserConverter.convert(request);
        newUser.setId(lastId.getAndIncrement());
        userCache.put(newUser.getId(), newUser);
        return newUser;
    }

    @ApiOperation("更新用户")
    @PutMapping
    public User update(User user) {
        if (user.getId() == null || user.getId() <= 0) {
            throw new BadRequestException("id is not present");
        }
        User oldUser = userCache.get(user.getId());
        if (oldUser == null)
            throw new BadRequestException("user not found");
        BeanUtils.copyProperties(user, oldUser);
        return oldUser;
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public User delete(@PathVariable Integer id) {
        return userCache.remove(id);
    }

}
