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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laoyeye.net
 * @Description: 公告通知
 * @date 2019/5/7 21:46
 */
@Controller
@RequestMapping("/admin")
public class NoticeController extends BaseController{
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice")
    public String index(Model model) {

        return "admin/notice";
    }

    @GetMapping("/notice/manage")
    public String noticeManage(Model model) {

        return "admin/notice_manage";
    }

    @RequestMapping("/notice/list")
    @ResponseBody
    public Result list(BaseQuery query,String startDate,String endDate,String noticeTitle) {
        Page<SysNoticeDO> page = noticeService.list(query, startDate, endDate, noticeTitle);
        return result(page);
    }
}
