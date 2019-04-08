//package net.laoyeye.yyms.realm;
//
//import com.aitou.oms.common.ApplicationContextRegister;
//import com.aitou.oms.common.utils.HttpClientUtil;
//import com.aitou.oms.common.utils.JSONUtils;
//import com.aitou.oms.common.utils.StringUtils;
//import com.aitou.oms.mapper.operation.CrmSysUserMapper;
//import com.aitou.oms.pojo.CrmSysUserDO;
//import com.aitou.oms.service.system.MenusService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//@Slf4j
//public class UserRealm extends AuthorizingRealm {
//    @Value("${login.url}")
//    private String login;
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//        String userName = ((CrmSysUserDO) SecurityUtils.getSubject().getPrincipal()).getUserName();
//        MenusService menuService = ApplicationContextRegister.getBean(MenusService.class);
//        Set<String> perms = menuService.listPerms(userName);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(perms);
//        return info;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String username = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());
//        CrmSysUserDO user = null;
//        String result = null;
//        try {
//            if (StringUtils.isEmpty(username) && StringUtils.isEmpty(password)) {
//                throw new UnknownAccountException("账号或密码不正确");
//            }
//            CrmSysUserMapper userMapper = ApplicationContextRegister.getBean(CrmSysUserMapper.class);
//            user = userMapper.getSysUserByUserName(username);
//            if (user == null) {
//                throw new UnknownAccountException("账号未授权，请联系管理员处理！");
//            }
//            Map<String,String> map = new HashMap<String,String>();
//            map.put("userName", username);
//            map.put("password", password);
//            String jsonString = JSONUtils.mapToJson(map);
//            log.info(username+":请求登录系统");
//            result = HttpClientUtil.doPostJson(login, jsonString);
//            log.info("互金返回数据："+result);
//        } catch (UnknownAccountException e) {
//           log.error(e.getMessage());
//           throw new UnknownAccountException("账号未授权，请联系管理员处理！");
//        } catch (Exception e) {
//           log.error(e.getMessage());
//           throw new UnknownAccountException("登录异常，请联系管理员处理！");
//        }
//        Map<String, Object> resultMap = null;
//        try {
//            resultMap = JSONUtils.jsonToMap(result);
//        } catch (Exception e) {
//            log.info("互金接口异常！");
//            throw new UnknownAccountException("互金接口异常！请稍后重试！");
//        }
//        if (resultMap.get("ret").equals("1")) {
//            log.info(username+":登录成功");
//        } else {
//            log.info(username+":登录失败，账号或密码不正确");
//            throw new UnknownAccountException("账号或密码不正确");
//        }
//
//        //不使用shiro自带的密码验证
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
//        return info;
//    }
//
//}
//
