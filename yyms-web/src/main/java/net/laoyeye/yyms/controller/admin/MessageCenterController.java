package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class MessageCenterController extends BaseController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/message/center")
    public String index(Model model) {

        return "admin/message_center";
    }

    @PostMapping("/message/list")
    @ResponseBody
    public Result list(BaseQuery query) {
        Page<SysNoticeDO> page = noticeService.list(query);
        return result(page);
    }
}
