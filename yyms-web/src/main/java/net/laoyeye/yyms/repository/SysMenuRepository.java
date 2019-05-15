package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @description: 菜单数据访问
 * @author: laoyeye.net
 * @date: 2019/4/29 23:24
 */
public interface SysMenuRepository extends JpaRepository<SysMenuDO, Long> {
    /**查询所有菜单，根据排序号升序*/
    List<SysMenuDO> findAllByOrderBySortAsc();
    /**批量删除菜单*/
    @Modifying
    @Transactional
    @Query("delete from SysMenuDO where id in (?1)")
    int deleteBatch(Long[] ids);
    /**根据父菜单ID查询菜单*/
    @Query(value = "select * from sys_menu where pid = :pid and status = true order by sort", nativeQuery = true)
    List<SysMenuDO> findByPidOrderBySortAsc(@Param("pid") Long pid);

}
