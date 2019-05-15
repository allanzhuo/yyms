package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysNoticeRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: 通知公告发送记录
 * @author: laoyeye.net
 * @date: 2019/5/15 21:44
 */
public interface SysNoticeRecordRepository extends JpaRepository<SysNoticeRecordDO, Long> {

}
