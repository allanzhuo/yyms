package net.laoyeye.yyms.repository;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.pojo.domain.SysNoticeRecordDO;
import net.laoyeye.yyms.pojo.vo.MessageCenterVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @description: 通知公告发送记录
 * @author: laoyeye.net
 * @date: 2019/5/15 21:44
 */
public interface SysNoticeRecordRepository extends JpaRepository<SysNoticeRecordDO, Long> {
    /**根据父菜单ID查询菜单*/
    @Query(value = "SELECT sn.notice_title,sn.create_time FROM sys_notice_record snr LEFT JOIN sys_notice sn ON sn.id = snr.notice_id AND sn.notice_status = TRUE WHERE snr.user_id = '1' ORDER BY sn.create_time DESC", nativeQuery = true)
    List<MessageCenterVO> findMessageCenter();
}
