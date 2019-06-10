package net.laoyeye.yyms.controller;


import lombok.extern.slf4j.Slf4j;
import net.laoyeye.enums.ResultEnum;
import net.laoyeye.pojo.Constant;
import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录
 * @author laoyeye.net
 * @date 2018年1月14日
 */
@Slf4j
@Controller
public class LoginController extends BaseController{

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, Boolean rememberMe) {
        if (rememberMe == null) {
            rememberMe = false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            token.setRememberMe(rememberMe);
            subject.login(token);
        } catch (Exception e) {
            log.error(username+"登录异常：", e);
            return Result.build(ResultEnum.UN_AUTHORIZED.getCode(), e.getMessage());
        }
        return Result.ok();
    }

    @GetMapping("/logout")
    public String logout(String from) {
        logout();
        if (StringUtils.isEmpty(from)) {
            return "redirect:/";
        }
        return "redirect:" + Constant.LOGIN_URL;
    }
}
