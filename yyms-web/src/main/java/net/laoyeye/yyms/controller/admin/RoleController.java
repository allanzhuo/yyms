package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
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
@RequestMapping("/admin/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Model model) {

        return "admin/role";
    }

    @GetMapping("/add")
    public String roleAdd(Model model) {
        model.addAttribute("url","/admin/role/add");
        return "admin/role_add";
    }

    @GetMapping("/edit")
    public String roleEdit(Model model,Long id) {
        model.addAttribute("url","/admin/role/edit");
        model.addAttribute("role", roleService.getRole(id));
        return "admin/role_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result addRole(SysRoleDO roleDO,@RequestParam("menuIds[]") Long[] menuIds) {
        roleDO.setCreateUser(getUser().getUserName());
        roleDO.setUpdateUser(getUser().getUserName());
        return roleService.saveOrUpdateRole(roleDO,menuIds);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result editRole(SysRoleDO roleDO,@RequestParam("menuIds[]") Long[] menuIds) {
        roleDO.setUpdateUser(getUser().getUserName());
        return roleService.saveOrUpdateRole(roleDO,menuIds);
    }


    @PostMapping("/list")
    @ResponseBody
    public Result list(BaseQuery query, String roleName) {
        Page<SysRoleDO> page = roleService.listByRoleName(query, roleName);
        return result(page);
    }

    @PostMapping("/edit/status")
    @ResponseBody
    public Result editNoticeStatus(Boolean roleStatus, Long id) {

        return roleService.updateStatusById(roleStatus, id);
    }

    @PostMapping("/remove")
    @ResponseBody
    public Result removeMenu(Long id) {

        return roleService.removeRole(id);
    }

    @PostMapping("/removeBatch")
    @ResponseBody
    public Result removeBatch(@RequestParam("ids[]") Long[] ids) {

        return roleService.removeBatch(ids);
    }
}
