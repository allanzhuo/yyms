package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @description: 菜单数据访问
 * @author: laoyeye.net
 * @date: 2019/4/29 23:24
 */
public interface SysMenuRepository extends JpaRepository<SysMenuDO, Long> {
    List<SysMenuDO> findAllByOrderBySortAsc();
}
