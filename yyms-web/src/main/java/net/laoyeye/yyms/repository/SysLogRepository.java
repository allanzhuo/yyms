package net.laoyeye.yyms.repository;

import java.util.List;
import net.laoyeye.yyms.pojo.domain.SysLogDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 日志记录
 * @author: laoyeye.net
 * @date: 2019/3/26 20:24
 */
public interface SysLogRepository extends JpaRepository<SysLogDO, Long>,JpaSpecificationExecutor<SysLogDO> {

    /**根据userName查询*/
    List<SysLogDO> findByUserName(String userName);
}
