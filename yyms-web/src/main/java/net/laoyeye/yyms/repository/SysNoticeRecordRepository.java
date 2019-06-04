package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysNoticeRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @description: 通知公告发送记录
 * @author: laoyeye.net
 * @date: 2019/5/15 21:44
 */
public interface SysNoticeRecordRepository extends JpaRepository<SysNoticeRecordDO, Long> {

    @Query(value = "SELECT sn.notice_title noticeTitle,sn.create_time createTime,snr.read_flg readFlg FROM sys_notice_record snr LEFT JOIN sys_notice sn ON sn.id = snr.notice_id AND sn.notice_status = TRUE WHERE snr.user_id = '1' ORDER BY sn.create_time DESC LIMIT :pageOffset,:pageSize", nativeQuery = true)
    List<Map<String,Object>> findMessageCenter(@Param("pageOffset") int pageOffset, @Param("pageSize")int pageSize);

    @Query(value = "SELECT count(snr.id) FROM sys_notice_record snr LEFT JOIN sys_notice sn ON sn.id = snr.notice_id AND sn.notice_status = TRUE WHERE snr.user_id = '1' ORDER BY sn.create_time DESC", nativeQuery = true)
    Long findMessageCenterNum();
}
