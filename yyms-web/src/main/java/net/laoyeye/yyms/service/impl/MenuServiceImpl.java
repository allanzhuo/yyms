package net.laoyeye.yyms.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.repository.SysMenuRepository;
import net.laoyeye.yyms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
