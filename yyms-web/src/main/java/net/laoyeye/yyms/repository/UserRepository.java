package net.laoyeye.yyms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.laoyeye.yyms.pojo.domain.SysUserDO;


/**
 * @description: 用户类数据访问
 * @author: laoyeye.net
 * @date: 2019/3/26 18:24
 */
public interface UserRepository extends JpaRepository<SysUserDO, Long> {
}
