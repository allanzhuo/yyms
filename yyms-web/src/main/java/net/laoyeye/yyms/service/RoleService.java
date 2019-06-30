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
    /**修改角色状态*/
    Result updateStatusById(Boolean roleStatus,Long id);
    /**批量删除角色*/
    Result removeBatch(Long[] ids);
    /**删除角色*/
    Result removeRole(Long id);
    /**保存角色*/
    Result saveOrUpdateRole(SysRoleDO roleDO,Long[] menuIds);
    /**查询角色*/
    SysRoleDO getRole(Long id);
}
