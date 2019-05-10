package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 通知公告
 * @author: laoyeye.net
 * @date: 2019/5/10 14:44
 */
public interface SysNoticeRepository extends JpaRepository<SysNoticeDO, Long>,JpaSpecificationExecutor<SysNoticeDO> {
}
