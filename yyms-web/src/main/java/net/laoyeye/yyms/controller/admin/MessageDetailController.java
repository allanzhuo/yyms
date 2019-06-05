package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laoyeye
 * @Description: 消息中心
 * @date 2019/6/2 22:03
 */
@Controller
@RequestMapping("/admin")
public class MessageDetailController extends BaseController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message/detail")
    public String index(Model model) {
        return "admin/message_detail";
    }

    @PostMapping("/message/readDetail")
    @ResponseBody
    public Result read(String recordId, String noticeId) {
        Result result = messageService.getMessageDetail(Long.parseLong(recordId.substring(0,recordId.indexOf("S"))),
                Long.parseLong(noticeId.substring(0,noticeId.indexOf("S"))));
        return result;
    }
}
