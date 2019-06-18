package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laoyeye.net
 * @Description: 基本资料设置
 * @date 2019/6/10 23:12
 */
@Controller
@RequestMapping("/admin")
public class OptionsController extends BaseController{
    @Autowired
    private UserService userService;

    @GetMapping("/options")
    public String index(Model model) {
        //model.addAttribute("user", getUser());
        return "admin/options";
    }

//    @PostMapping("/account/edit")
//    @ResponseBody
//    public Result edit(SysUserDO userDO) {
//        userDO.setUpdateUser(getUser().getUserName());
//        Result result = userService.updateAccount(userDO);
//        return result;
//    }
}
