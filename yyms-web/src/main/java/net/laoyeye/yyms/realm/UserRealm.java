package net.laoyeye.yyms.realm;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.repository.SysUserRepository;
import net.laoyeye.yyms.service.MenuService;
import net.laoyeye.yyms.utils.SpringBeanFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.DigestUtils;

import java.util.Optional;
import java.util.Set;

/**
 * shiro自定义Realm
 * laoyeye.net
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        Long userId = ((SysUserDO)SecurityUtils.getSubject().getPrincipal()).getId();
        MenuService menuService = SpringBeanFactory.getBean(MenuService.class);
        Set<String> perms = menuService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        log.info(username+"：请求登录！");
        String password = new String((char[]) token.getCredentials());

        SysUserRepository userRepository = SpringBeanFactory.getBean(SysUserRepository.class);
        // 查询用户信息
        Optional<SysUserDO> user = null;
        if (username.length() > 12) {
            user = userRepository.findByQqOpenId(username);
            // 账号不存在
            user.orElseThrow(()->new UnknownAccountException("账号或密码不正确"));
            // 账号锁定
            if (user.get().getStatus() == false) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
        } else {
            user = userRepository.findByUserName(username);
            // 账号不存在
            user.orElseThrow(()->new UnknownAccountException("账号或密码不正确"));

            // 密码错误
            if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.get().getPassword())) {
                throw new IncorrectCredentialsException("账号或密码不正确");
            }

            // 账号锁定
            if (user.get().getStatus() == false) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }
        }
        log.info(username+"：登录成功！");
        SysUserDO userDO = user.get().toBuilder().password(null).build();
        //不使用shiro自带的密码验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDO, password, getName());
        return info;
    }

}

