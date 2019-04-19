package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @description: 用户类数据访问
 * @author: laoyeye.net
 * @date: 2019/3/26 18:24
 */
public interface UserRepository extends JpaRepository<SysUserDO, Long> {
    Optional<SysUserDO> findByQqOpenId(String qqOpenId);

    Optional<SysUserDO> findByUserName(String userName);
}
