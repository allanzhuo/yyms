package net.laoyeye.yyms.controller;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;

public class BaseController{

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static SysUserDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (SysUserDO)object;
    }

    public static Long getUserId() {
        return getUser().getId();
    }

    public static void logout() {
        getSubjct().logout();
    }

    /**
     * 转换为自定义响应体
     */
    protected static Result result(Page jpaPage) {

        return new Result(jpaPage.getContent(), (long)jpaPage.getTotalElements());
    }

}
