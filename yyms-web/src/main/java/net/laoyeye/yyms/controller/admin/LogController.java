package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysLogDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laoyeye.net
 * @Description: 角色管理
 * @date 2019/6/27 21:55
 */
@Controller
@RequestMapping("/admin/log")
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    @GetMapping
    public String index() {

        return "admin/log";
    }

    @PostMapping("/list")
    @ResponseBody
    public Result list(BaseQuery query, String userName,String operation) {
        Page<SysLogDO> page = logService.list(query,userName,operation);
        return result(page);
    }

}
