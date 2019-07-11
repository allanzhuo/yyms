package net.laoyeye.yyms.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 请求错误页面
 * @author laoyeye.net
 * @date 2018年1月14日
 */
@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController{

    @GetMapping("/500")
    public String errorPage() {

        return "error/500";
    }

    @GetMapping("/404")
    public String notFondPage() {

        return "error/404";
    }

    @GetMapping("/403")
    public String unAuthorized() {

        return "error/403";
    }

}
