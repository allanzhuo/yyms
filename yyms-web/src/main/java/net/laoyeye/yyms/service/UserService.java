package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;

/**
 * @author laoyeye
 * @Description: 用户service
 * @date 2019/6/9 19:29
 */
public interface UserService {
    Result updatePassword(String oldPassword,String password,String repassword,Long userId);
}
