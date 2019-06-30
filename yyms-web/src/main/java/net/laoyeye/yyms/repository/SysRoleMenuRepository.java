package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.domain.SysRoleMenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @description: 用户类数据访问
 * @author: laoyeye.net
 * @date: 2019/3/26 18:24
 */
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenuDO, Long>,JpaSpecificationExecutor<SysRoleMenuDO> {
    /**批量删除通知*/
    @Modifying
    @Transactional
    @Query("delete from SysRoleMenuDO where roleCode = ?1")
    int deleteByRoleCode(String roleCode);
}
