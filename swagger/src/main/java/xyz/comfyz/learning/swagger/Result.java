package xyz.comfyz.learning.swagger;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author :    comfy
 * @date :      2018-05-07 17:19:10
 * @since :     1.8
 * <p>
 */
@ApiModel
public class Result<T> {

    private List<T> list;

    public Result(List<T> pricipal) {
        list = pricipal;
    }

    public List<T> getList() {
        return list;
    }
}
