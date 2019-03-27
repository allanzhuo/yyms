package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.SysUserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: 用户类数据访问
 * @author: laoyeye
 * @date: 2019/3/26 18:24
 */
public interface UserRepository extends JpaRepository<SysUserDO, Long> {
}
