package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import org.springframework.data.domain.Page;

/**
 * @author laoyeye
 * @Description: 角色管理
 * @date 2019/6/27 22:03
 */
public interface RoleService {
    /**分页查询角色*/
    Page<SysRoleDO> listByRoleName(BaseQuery query, String roleName);
}
