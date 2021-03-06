package net.laoyeye.pojo;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
/**
 * 通用响应体
 * @author laoyeye.net
 * @date 2018年3月19日
 */
@Getter
@Setter
public class Result {
    private Integer code;
    private String msg;
    private Long count;
    private List<?> data;
    
    public static Result build(Integer code, String msg, List<?> data) {
        return new Result(code, msg, data);
    }

    public static Result ok(List<?> data,String msg) {
        return new Result(data,msg);
    }

    public static Result ok(String msg) {
        return new Result(null,msg);
    }

    public static Result ok() {
        return new Result(null);
    }
    public Result() {

    }

    public static Result build(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public Result(Integer code, String msg, List<?> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(List<?> data) {
        this.code = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Result(List<?> data,String msg) {
        this.code = 200;
        this.msg = msg;
        this.data = data;
    }

    public Result(List<?> data,Long count) {
        this.code = 0;
        this.count = count;
        this.data = data;
    }
}
