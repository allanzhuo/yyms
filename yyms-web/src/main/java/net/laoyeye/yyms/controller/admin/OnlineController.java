package net.laoyeye.yyms.controller.admin;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.enums.ResultEnum;
import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.annotation.Log;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.service.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laoyeye
 * @Description: 在线用户
 * @date 2019/7/13 11:08
 */
@Slf4j
@Controller
@RequestMapping("/admin/online")
public class OnlineController {
    @Autowired
    private OnlineService onlineService;

    @GetMapping
    public String index() {

        return "admin/online";
    }

    @ResponseBody
    @RequestMapping("/list")
    public Result list(BaseQuery query, String userName) {
        return onlineService.list(query, userName);
    }

    @Log("强制退出")
    @ResponseBody
    @RequestMapping("/remove/{sessionId}")
    public Result forceLogout(@PathVariable("sessionId") String sessionId) {
        try {
            onlineService.removeUser(sessionId);
            return Result.ok("退出成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("强制退出失败！",e);
            return Result.build(ResultEnum.UNKONW_ERROR.getCode(), "强制退出失败！请联系管理员。");
        }

    }
}
