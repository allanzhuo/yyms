package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysOptionsDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @description: 系统配置
 * @author: laoyeye.net
 * @date: 2019/6/21 20:55
 */
public interface SysOptionsRepository extends JpaRepository<SysOptionsDO, Long> {
    Optional<SysOptionsDO> findByOptionCode(String optionCode);
}
