package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description: 后台菜单
 * @author: zhangzhuo
 * @date: 2019/4/24 19:59
 */
@Controller
@RequestMapping("/admin")
public class MenuController extends BaseController{
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public String index(Model model) {

        return "admin/menu";
    }

    @GetMapping("/menu/add")
    public String add(Model model) {

        return "admin/menu_add";
    }

    @RequestMapping("/menu/list")
    @ResponseBody
    public List<SysMenuDO> listMenu() {

        return menuService.listMenus();
    }

    @PostMapping("/menu/edit/status")
    @ResponseBody
    public Result editMenuStatus(Boolean status, Long id) {

        return menuService.updateStatusById(status,id);
    }

    @PostMapping("/menu/add")
    @ResponseBody
    public Result addMenu(SysMenuDO menuDO) {
        menuDO.setCreateUser(getUser().getUserName());
        menuDO.setUpdateUser(getUser().getUserName());
        return menuService.saveMenu(menuDO);
    }
}
