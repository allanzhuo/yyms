package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @description: 用户类数据访问
 * @author: laoyeye.net
 * @date: 2019/3/26 18:24
 */
public interface SysRoleRepository extends JpaRepository<SysRoleDO, Long>,JpaSpecificationExecutor<SysRoleDO> {
    /**批量删除通知*/
    @Modifying
    @Transactional
    @Query("delete from SysRoleDO where id in (?1)")
    int deleteBatch(Long[] ids);
    /**根据状态查询所有角色*/
    List<SysRoleDO> findAllByRoleStatus(Boolean roleStatus);
    /**根据角色Code角色查询*/
    SysRoleDO findByRoleCode(String roleCode);
}
