package net.laoyeye.yyms.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.repository.SysMenuRepository;
import net.laoyeye.yyms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @author laoyeye
 * @Description: 后台菜单service
 * @date 2019/4/29 23:09
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Override
    public List<SysMenuDO> listMenus() {
        return sysMenuRepository.findAllByOrderBySortAsc();
    }

    @Override
    public Result updateStatusById(Boolean status, Long id) {
        Optional<SysMenuDO> menuDO = sysMenuRepository.findById(id);
        if (menuDO.isPresent()){
            SysMenuDO menu = menuDO.get().toBuilder()
                    .status(status)
                    .build();
            SysMenuDO newMenu = sysMenuRepository.save(menu);
        }
        return Result.ok("修改菜单状态成功！");
    }

    @Override
    public Result saveMenu(SysMenuDO menuDO) {
        sysMenuRepository.save(menuDO);
        return Result.ok("新增菜单成功！");
    }

    @Override
    public Result updateMenu(SysMenuDO menuDO) {
        sysMenuRepository.save(menuDO);
        return Result.ok("修改菜单成功！");
    }

    @Override
    public SysMenuDO getMenuById(Long id) {
        SysMenuDO menuDO = sysMenuRepository.findById(id).orElse(new SysMenuDO());
        return menuDO;
    }

    @Override
    public Result removeMenu(Long[] ids) {
        int i = sysMenuRepository.deleteBatch(ids);
        return Result.ok("删除选中的【" + i + "】条数据成功!");
    }
}
