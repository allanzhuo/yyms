package net.laoyeye.yyms.controller.admin;

import net.laoyeye.yyms.controller.BaseController;
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
public class AccountController extends BaseController{

    @GetMapping("/account")
    public String index(Model model) {
        model.addAttribute("user", getUser());
        return "admin/account";
    }
}
