package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @description: 用户类数据访问
 * @author: laoyeye.net
 * @date: 2019/3/26 18:24
 */
public interface SysUserRepository extends JpaRepository<SysUserDO, Long>,JpaSpecificationExecutor<SysUserDO> {
    Optional<SysUserDO> findByQqOpenId(String qqOpenId);

    Optional<SysUserDO> findByUserName(String userName);

    /**批量删除通知*/
    @Modifying
    @Transactional
    @Query("delete from SysUserDO where id in (?1)")
    int deleteBatch(Long[] ids);
}
