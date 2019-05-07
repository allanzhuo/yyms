package net.laoyeye.yyms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laoyeye.net
 * @Description: 公告通知
 * @date 2019/5/7 21:46
 */
@Controller
@RequestMapping("/admin")
public class NoticeController {

    @GetMapping("/notice")
    public String index(Model model) {

        return "admin/notice";
    }
}
