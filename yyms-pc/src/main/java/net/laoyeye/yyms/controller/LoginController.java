package net.laoyeye.yyms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录
 * @author zhangzhuo
 * @date 2018年1月14日
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {

        return "login";
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public Result login(String username, String password, Boolean rememberMe) {
//        if (rememberMe == null) {
//            rememberMe = false;
//        }
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            token.setRememberMe(rememberMe);
//            subject.login(token);
//        } catch (Exception e) {
//            return Result.build(ResultEnum.UNKONW_ERROR.getCode(), e.getMessage());
//        }
//        return Result.ok();
//    }

/*    @GetMapping("/logout")
    public String logoutUser() {
        logout();
        return "redirect:/login";
    }*/
}
