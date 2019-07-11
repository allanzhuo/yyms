package net.laoyeye.yyms.service;

import net.laoyeye.yyms.pojo.domain.SysLogDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import org.springframework.data.domain.Page;

/**
 * @description: 日志管理
 * @author: zhangzhuo
 * @date: 2019/7/11 19:41
 */
public interface LogService {
    /**分页查询角色*/
    Page<SysLogDO> list(BaseQuery query, String userName,String operation);
}
