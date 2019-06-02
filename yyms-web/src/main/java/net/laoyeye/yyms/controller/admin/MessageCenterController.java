package net.laoyeye.yyms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author laoyeye
 * @Description: 消息中心
 * @date 2019/6/2 22:03
 */
@Controller
@RequestMapping("/admin")
public class MessageCenterController {

    @GetMapping("/message/center")
    public String index(Model model) {

        return "admin/message_center";
    }
}
