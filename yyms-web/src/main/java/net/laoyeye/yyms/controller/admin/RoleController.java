package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
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
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public String index(Model model) {

        return "admin/role";
    }

    @GetMapping("/role/add")
    public String roleAdd(Model model) {
        model.addAttribute("url","/admin/role/add");
        return "admin/role_add";
    }

    @GetMapping("/role/edit")
    public String roleEdit(Model model,Long id) {
        model.addAttribute("url","/admin/role/edit");
        model.addAttribute("role", roleService.getRole(id));
        return "admin/role_add";
    }

    @PostMapping("/role/add")
    @ResponseBody
    public Result addRole(SysRoleDO roleDO,@RequestParam("menuIds[]") Long[] menuIds) {
        roleDO.setCreateUser(getUser().getUserName());
        roleDO.setUpdateUser(getUser().getUserName());
        return roleService.saveOrUpdateRole(roleDO,menuIds);
    }

    @PostMapping("/role/edit")
    @ResponseBody
    public Result editRole(SysRoleDO roleDO,@RequestParam("menuIds[]") Long[] menuIds) {
        roleDO.setUpdateUser(getUser().getUserName());
        return roleService.saveOrUpdateRole(roleDO,menuIds);
    }


    @PostMapping("/role/list")
    @ResponseBody
    public Result list(BaseQuery query, String roleName) {
        Page<SysRoleDO> page = roleService.listByRoleName(query, roleName);
        return result(page);
    }

    @PostMapping("/role/edit/status")
    @ResponseBody
    public Result editNoticeStatus(Boolean roleStatus, Long id) {

        return roleService.updateStatusById(roleStatus, id);
    }

    @PostMapping("/role/remove")
    @ResponseBody
    public Result removeMenu(Long id) {

        return roleService.removeRole(id);
    }

    @PostMapping("/role/removeBatch")
    @ResponseBody
    public Result removeBatch(@RequestParam("ids[]") Long[] ids) {

        return roleService.removeBatch(ids);
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
