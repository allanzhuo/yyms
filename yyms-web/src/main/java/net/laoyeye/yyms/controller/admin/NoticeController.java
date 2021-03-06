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
import org.springframework.web.bind.annotation.*;

/**
 * @author laoyeye.net
 * @Description: 公告通知
 * @date 2019/5/7 21:46
 */
@Controller
@RequestMapping("/admin/notice")
public class NoticeController extends BaseController{
    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public String index(Model model) {

        return "admin/notice";
    }

    @GetMapping("/add")
    public String noticeAdd(Model model) {

        return "admin/notice_add";
    }

    @PostMapping("/list")
    @ResponseBody
    public Result list(BaseQuery query,String startDate,String endDate,String noticeTitle) {
        Page<SysNoticeDO> page = noticeService.listByCriteria(query, startDate, endDate, noticeTitle);
        return result(page);
    }

    @PostMapping("/save")
    @ResponseBody
    public Result save(SysNoticeDO noticeDO) {
        noticeDO.setCreateUser(getUser().getUserName());
        noticeDO.setUpdateUser(getUser().getUserName());
        Result result = noticeService.save(noticeDO);
        return result;
    }

    @PostMapping("/edit/status")
    @ResponseBody
    public Result editNoticeStatus(Boolean noticeStatus, Long id) {

        return noticeService.updateStatusById(noticeStatus,id);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result editNotice(SysNoticeDO noticeDO) {
        noticeDO.setUpdateUser(getUser().getUserName());
        return noticeService.updateNotice(noticeDO);
    }

    @PostMapping("/remove")
    @ResponseBody
    public Result removeMenu(Long id) {

        return noticeService.removeNotice(id);
    }

    @PostMapping("/removeBatch")
    @ResponseBody
    public Result removeBatch(@RequestParam("ids[]") Long[] ids) {

        return noticeService.removeBatch(ids);
    }
}
