package net.laoyeye.yyms.controller.admin;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年3月9日
 * @website www.laoyeye.net
 */
@Slf4j
@Controller
public class AdminIndexController extends BaseController {

//    @Autowired
//    private ArticleService articleService;
//    @Autowired
//    private CateService cateService;
//    @Autowired
//    private CommentService commentService;
//    @Autowired
//    private NoteService noteService;

//    @RequiresPermissions("blog:manage:index")
    @GetMapping("/admin")
    public String index(Model model) {
        SysUserDO user = getUser();
        model.addAttribute("avatar", user.getAvatar());
        model.addAttribute("nickName", user.getNickName());
        return "admin/admin_index";
    }

//    @GetMapping("/home")
//    public String home(Model model) {
//        model.addAttribute("articleCnt", articleService.countAllArticle());
//        model.addAttribute("noteCnt", noteService.countAllNote());
//        model.addAttribute("commentCnt", commentService.countAllComment());
//        model.addAttribute("latestArticle", articleService.getLatestArticle());
//        model.addAttribute("latestNote", noteService.getLatestNote());
//        model.addAttribute("latestComment", commentService.getLatestComment());
//        model.addAttribute("cateList", cateService.listAllCate());
//        return "management/home";
//    }

//    @Log("保存文章")
//    @RequiresPermissions("blog:blog:add")
//    @PostMapping("/simple/add/article")
//    @ResponseBody
//    public YYBlogResult simplePostArticle(ArticleDO article) {
//        if (article.getContent().length() > 300) {
//            return YYBlogResult.build(500, "草稿字数不宜过多！");
//        }
//        UserDO user = getUser();
//        article.setAuthorId(user.getId());
//        return articleService.saveSimpleArticle(article);
//    }
//
//    @Log("保存笔记")
//    @RequiresPermissions("blog:note:add")
//    @PostMapping("/simple/add/note")
//    @ResponseBody
//    public YYBlogResult simplePostNote(NoteDO note) {
//        if (note.getContent().length() > 300) {
//            return YYBlogResult.build(500, "笔记字数不宜过多！");
//        }
//        return noteService.saveNote(note, null);
//    }
//
//    @GetMapping("/logout")
//    public String logout(String from) {
//        logout();
//        if (StringUtils.isEmpty(from)) {
//            return "redirect:/";
//        } else {
//            return "redirect:" + Constant.MANAGEMENT_INDEX;
//        }
//    }
}
