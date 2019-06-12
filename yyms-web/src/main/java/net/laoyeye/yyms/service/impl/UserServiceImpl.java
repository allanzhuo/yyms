package net.laoyeye.yyms.service.impl;

import net.laoyeye.enums.ResultEnum;
import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.repository.SysUserRepository;
import net.laoyeye.yyms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

/**
 * @author laoyeye
 * @Description: 用户信息service
 * @date 2019/6/9 19:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public Result updatePassword(String oldPassword,String password,String repassword,Long userId) {
        Optional<SysUserDO> user = sysUserRepository.findById(userId);
        SysUserDO userDO = user.get();
        if (!userDO.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))){
            return Result.build(ResultEnum.NOT_FOUND.getCode(),"当前密码错误，请检查！");
        }
        if (password.equals(repassword)) {
            userDO.setUpdateUser(userDO.getUserName());
            if (!StringUtils.isEmpty(password)) {
                userDO.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            }
            sysUserRepository.save(userDO);
            return Result.ok("密码修改成功，请重新登录！");
        }
        return Result.build(500, "密码验证不一致，请检查！");
    }

    @Override
    public Result updateAccount(SysUserDO userDO) {
        Optional<SysUserDO> sysUserDO = sysUserRepository.findById(userDO.getId());
        SysUserDO user = sysUserDO.get();
        SysUserDO newUser = user.toBuilder()
                .userName(userDO.getUserName())
                .nickName(userDO.getNickName())
                .avatar(userDO.getAvatar())
                .email(userDO.getEmail())
                .profile(userDO.getProfile())
                .build();
        sysUserRepository.save(newUser);
        return Result.ok("设置资料成功！");
    }
}
