package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import org.springframework.data.domain.Page;

/**
 * @author laoyeye
 * @Description: 用户service
 * @date 2019/6/9 19:29
 */
public interface UserService {
    /**修改密码*/
    Result updatePassword(String oldPassword,String password,String repassword,Long userId);
    /**修改个人资料*/
    Result updateAccount(SysUserDO userDO);
    /**分页查询用户*/
    Page<SysUserDO> listByNickName(BaseQuery pageQuery, String nickName);
    /**根据id查询用户*/
    SysUserDO getUserById(Long id);
    /**修改用户状态*/
    Result updateStatusById(Boolean status,Long id);
    /**删除用户*/
    Result remove(Long id);
    /**批量删除用户*/
    Result removeBatch(Long[] ids);
    /**新增或删除用户*/
    Result saveOrUpdateRole(SysUserDO userDO);
}
