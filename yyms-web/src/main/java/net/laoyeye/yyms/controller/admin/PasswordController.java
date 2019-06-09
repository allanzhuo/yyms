package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laoyeye
 * @Description: 修改密码
 * @date 2019/6/9 18:50
 */
@Controller
@RequestMapping("/admin")
public class PasswordController extends BaseController{
    @Autowired
    private UserService userService;

    @GetMapping("/password")
    public String index(Model model) {

        return "admin/password";
    }

    @PostMapping("/password/edit")
    @ResponseBody
    public Result edit(String oldPassword,String password,String repassword) {
        Result result = userService.updatePassword(oldPassword, password, repassword, getUserId());
        if (result.getCode() == 200){
            logout();
        }
        return result;
    }
}
