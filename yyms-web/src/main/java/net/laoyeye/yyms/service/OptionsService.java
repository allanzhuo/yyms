package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysOptionsDO;

import java.util.List;
import java.util.Map;

/**
 * @author laoyeye.net
 * @Description: 系统配置
 * @date 2019/6/21 20:52
 */
public interface OptionsService {
    /**保存配置*/
    Result save(Map<String,String> map);

    /**查询所有配置*/
    List<SysOptionsDO> listAll();
}
