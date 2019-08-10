package net.laoyeye.yyms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 随笔页面
 * @author: zhangzhuo
 * @date: 2019/8/4 17:35
 */
@Controller
@RequestMapping("/admin/post")
public class PostController {
    @GetMapping("/edit")
    public String index(Model model) {

        return "admin/post_edit_nk";
    }
}
