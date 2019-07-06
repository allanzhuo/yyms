package net.laoyeye.yyms.controller.admin;

import net.laoyeye.yyms.pojo.domain.SysUserDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laoyeye
 * @Description: 控制台
 * @date 2019/4/21 18:37
 */
@Controller
@RequestMapping("/admin/console")
public class ConsoleController {
    @GetMapping
    public String index(Model model) {

        return "admin/console";
    }
}
