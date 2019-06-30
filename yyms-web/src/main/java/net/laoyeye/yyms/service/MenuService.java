package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.pojo.vo.SysMenuVO;

import java.util.List;

/**
 * @author laoyeye.net
 * @Description: 系统菜单service
 * @date 2019/4/16 20:37
 */
public interface MenuService {
    /**查询所有的菜单*/
    List<SysMenuDO> listMenus();
    /**修改菜单状态*/
    Result updateStatusById(Boolean status,Long id);
    /**新增菜单*/
    Result saveMenu(SysMenuDO menuDO);
    /**修改菜单*/
    Result updateMenu(SysMenuDO menuDO);
    /**根据id查询菜单*/
    SysMenuDO getMenuById(Long id);
    /**根据id批量删除菜单*/
    Result removeMenu(Long[] ids);
    /**根据用户角色查询用户菜单*/
    Result listInitMenus();
    /**查询授权菜单*/
    Result listGrantMenus(String roleCode);
}
