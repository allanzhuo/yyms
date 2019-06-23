package net.laoyeye.yyms.controller.admin;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.controller.BaseController;
import net.laoyeye.yyms.pojo.domain.SysOptionsDO;
import net.laoyeye.yyms.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author laoyeye.net
 * @Description: 基本资料设置
 * @date 2019/6/10 23:12
 */
@Controller
@RequestMapping("/admin")
public class OptionsController extends BaseController{
    @Autowired
    private OptionsService optionsService;

    @GetMapping("/options")
    public String index(Model model) {
        List<SysOptionsDO> list = optionsService.listAll();
        Map<String, Object> attributeMap = new HashMap<String, Object>();
        for (SysOptionsDO optionsDO : list) {
            attributeMap.put(optionsDO.getOptionCode(), optionsDO.getOptionValue());
        }
        model.addAllAttributes(attributeMap);
        return "admin/options";
    }

    @PostMapping("/options/save")
    @ResponseBody
    public Result save(@RequestParam Map<String,String> map) {
        Result result = optionsService.save(map);
        return result;
    }
}
