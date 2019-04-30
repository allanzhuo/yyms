package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;

import java.util.List;

/**
 * @author laoyeye.net
 * @Description: 系统菜单service
 * @date 2019/4/16 20:37
 */
public interface MenuService {
    /**查询所有的菜单*/
    List<SysMenuDO> listMenus();
    /**修改菜单属性*/
    Result updateStatusById(Boolean status,Long id);
    /**新增菜单属性*/
    Result saveMenu(SysMenuDO menuDO);
    /**修改菜单属性*/
    Result updateMenu();
}
