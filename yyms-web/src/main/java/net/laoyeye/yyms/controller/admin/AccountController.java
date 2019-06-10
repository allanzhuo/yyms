package net.laoyeye.yyms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laoyeye.net
 * @Description: 基本资料设置
 * @date 2019/6/10 23:12
 */
@Controller
@RequestMapping("/admin")
public class AccountController {

    @GetMapping("/account")
    public String index(Model model) {

        return "admin/account";
    }
}
