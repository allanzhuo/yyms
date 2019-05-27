package net.laoyeye.yyms.repository;

import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @description: 通知公告
 * @author: laoyeye.net
 * @date: 2019/5/10 14:44
 */
public interface SysNoticeRepository extends JpaRepository<SysNoticeDO, Long>,JpaSpecificationExecutor<SysNoticeDO> {
    /**批量删除通知*/
    @Modifying
    @Transactional
    @Query("delete from SysNoticeDO where id in (?1)")
    int deleteBatch(Long[] ids);
}
