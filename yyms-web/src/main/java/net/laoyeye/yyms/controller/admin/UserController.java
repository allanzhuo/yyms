package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.service.RoleService;
import net.laoyeye.yyms.service.UserService;
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
@RequestMapping("/admin/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index() {

        return "admin/user";
    }

    @PostMapping("/list")
    @ResponseBody
    public Result list(BaseQuery query, String nickName) {
        Page<SysUserDO> page = userService.listByNickName(query, nickName);
        return result(page);
    }

    @PostMapping("/edit/status")
    @ResponseBody
    public Result editNoticeStatus(Boolean status, Long id) {

        return userService.updateStatusById(status, id);
    }

    @PostMapping("/remove")
    @ResponseBody
    public Result remove(Long id) {

        return userService.remove(id);
    }

    @PostMapping("/removeBatch")
    @ResponseBody
    public Result removeBatch(@RequestParam("ids[]") Long[] ids) {

        return userService.removeBatch(ids);
    }

    @GetMapping("/add")
    public String roleAdd(Model model) {
        model.addAttribute("url","/admin/user/add");
        model.addAttribute("roleList",roleService.listRoleByRoleStatus(Boolean.TRUE));
        return "admin/user_add";
    }

    @GetMapping("/edit")
    public String roleEdit(Model model,Long id) {
        model.addAttribute("url","/admin/user/edit");
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleList",roleService.listRoleByRoleStatus(Boolean.TRUE));
        return "admin/user_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result addUser(SysUserDO userDO) {
        userDO.setCreateUser(getUser().getUserName());
        userDO.setUpdateUser(getUser().getUserName());
        return userService.saveOrUpdateRole(userDO);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result editUser(SysUserDO userDO) {
        userDO.setUpdateUser(getUser().getUserName());
        return userService.saveOrUpdateRole(userDO);
    }

}
