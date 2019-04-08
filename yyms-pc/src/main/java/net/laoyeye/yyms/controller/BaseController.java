//package net.laoyeye.yyms.controller;
//
//import net.laoyeye.yyms.pojo.domain.SysUserDO;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//
//public class BaseController{
//
//    public static Subject getSubjct() {
//        return SecurityUtils.getSubject();
//    }
//
//    public static SysUserDO getUser() {
//        Object object = getSubjct().getPrincipal();
//        return (SysUserDO)object;
//    }
//
//    public static Long getUserId() {
//        return getUser().getId();
//    }
//
//    public static void logout() {
//        getSubjct().logout();
//    }
//
//}
