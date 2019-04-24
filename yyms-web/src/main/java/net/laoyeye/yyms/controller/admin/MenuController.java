package net.laoyeye.yyms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 后台菜单
 * @author: zhangzhuo
 * @date: 2019/4/24 19:59
 */
@Controller
@RequestMapping("/admin")
public class MenuController {

    @GetMapping("/menu")
    public String index(Model model) {

        return "admin/menu";
    }
}
