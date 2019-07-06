package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 后台菜单
 * @author: laoyeye.net
 * @date: 2019/4/24 19:59
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController{
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String index(Model model) {

        return "admin/menu";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<SysMenuDO> listMenu() {

        return menuService.listMenus();
    }

    @GetMapping("/add")
    public String add(Model model,Long pid,String title) {
        model.addAttribute("pid",pid);
        model.addAttribute("title",title);
        model.addAttribute("url","/admin/menu/add");
        return "admin/menu_add";
    }

    @GetMapping("/edit")
    public String edit(Model model,Long id) {
        SysMenuDO menuDO = menuService.getMenuById(id);
        SysMenuDO parentMenuDO;
        if (menuDO.getPid()==0L){
            parentMenuDO = SysMenuDO.builder().id(0L).title("顶级菜单").build();
        } else {
            parentMenuDO = menuService.getMenuById(menuDO.getPid());
        }
        model.addAttribute("pid", parentMenuDO.getId());
        model.addAttribute("title", parentMenuDO.getTitle());
        model.addAttribute("menu",menuDO);
        model.addAttribute("url","/admin/menu/edit");
        return "admin/menu_add";
    }

    @PostMapping("/edit/status")
    @ResponseBody
    public Result editMenuStatus(Boolean status, Long id) {

        return menuService.updateStatusById(status,id);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result addMenu(SysMenuDO menuDO) {
        menuDO.setCreateUser(getUser().getUserName());
        menuDO.setUpdateUser(getUser().getUserName());
        return menuService.saveMenu(menuDO);
    }

    @PostMapping("/edit")
    @ResponseBody
    public Result editMenu(SysMenuDO menuDO) {
        menuDO.setUpdateUser(getUser().getUserName());
        return menuService.updateMenu(menuDO);
    }

    @PostMapping("/remove")
    @ResponseBody
    public Result removeMenu(@RequestParam("ids[]")Long[] ids) {
        Result result = menuService.removeMenu(ids);
        return result;
    }

    @PostMapping("/init")
    @ResponseBody
    public Result listInitMenu() {
        Result result = menuService.listInitMenus();
        return result;
    }

    @PostMapping("/grant")
    @ResponseBody
    public Result listGrantMenus(String roleCode) {
        Result result = menuService.listGrantMenus(roleCode);
        return result;
    }
}
