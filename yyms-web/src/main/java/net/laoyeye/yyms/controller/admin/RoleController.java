package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.service.NoticeService;
import net.laoyeye.yyms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author laoyeye.net
 * @Description: 角色管理
 * @date 2019/6/27 21:55
 */
@Controller
@RequestMapping("/admin")
public class RoleController extends BaseController{
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public String index(Model model) {

        return "admin/role";
    }

//    @GetMapping("/notice/add")
//    public String noticeManage(Model model) {
//
//        return "admin/notice_add";
//    }
//
    @PostMapping("/role/list")
    @ResponseBody
    public Result list(BaseQuery query, String roleName) {
        Page<SysRoleDO> page = roleService.listByRoleName(query, roleName);
        return result(page);
    }
//
//    @PostMapping("/notice/save")
//    @ResponseBody
//    public Result save(SysNoticeDO noticeDO) {
//        noticeDO.setCreateUser(getUser().getUserName());
//        noticeDO.setUpdateUser(getUser().getUserName());
//        Result result = noticeService.save(noticeDO);
//        return result;
//    }
//
//    @PostMapping("/notice/edit/status")
//    @ResponseBody
//    public Result editNoticeStatus(Boolean noticeStatus, Long id) {
//
//        return noticeService.updateStatusById(noticeStatus,id);
//    }
//
//    @PostMapping("/notice/edit")
//    @ResponseBody
//    public Result editNotice(SysNoticeDO noticeDO) {
//        noticeDO.setUpdateUser(getUser().getUserName());
//        return noticeService.updateNotice(noticeDO);
//    }
//
//    @PostMapping("/notice/remove")
//    @ResponseBody
//    public Result removeMenu(Long id) {
//
//        return noticeService.removeNotice(id);
//    }
//
//    @PostMapping("/notice/removeBatch")
//    @ResponseBody
//    public Result removeBatch(@RequestParam("ids[]") Long[] ids) {
//
//        return noticeService.removeBatch(ids);
//    }
}