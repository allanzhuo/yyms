package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author laoyeye
 * @Description: 消息中心
 * @date 2019/6/2 22:03
 */
@Controller
@RequestMapping("/admin/message")
public class MessageController extends BaseController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/center")
    public String index(Model model) {
        model.addAttribute("unread",messageService.countUnread(getUserId()));
        return "admin/message_center";
    }

    @RequestMapping("/center/unread")
    @ResponseBody
    public int unread() {
        return messageService.countUnread(getUserId());
    }

    @PostMapping("/list")
    @ResponseBody
    public Result list(BaseQuery query) {
        Result result = messageService.list(query,getUserId());
        return result;
    }

    @PostMapping("/read")
    @ResponseBody
    public Result updateReadByIds(@RequestParam("ids[]") Long[] ids) {
        Result result = messageService.updateReadByIds(ids,getUserId());
        return result;
    }

    @PostMapping("/readAll")
    @ResponseBody
    public Result updateReadAll() {
        Result result = messageService.updateReadAll(getUserId());
        return result;
    }

    @PostMapping("/removeBatch")
    @ResponseBody
    public Result removeBatch(@RequestParam("ids[]") Long[] ids) {

        return messageService.removeBatch(ids,getUserId());
    }

    @GetMapping("/detail")
    public String detailIndex() {
        return "admin/message_detail";
    }

    @PostMapping("/readDetail")
    @ResponseBody
    public Result read(String recordId, String noticeId) {
        Result result = messageService.getMessageDetail(Long.parseLong(recordId.substring(0,recordId.indexOf("S"))),
                Long.parseLong(noticeId.substring(0,noticeId.indexOf("S"))));
        return result;
    }
}
