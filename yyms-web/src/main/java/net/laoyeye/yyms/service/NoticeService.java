package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import org.springframework.data.domain.Page;

/**
 * @author laoyeye.net
 * @Description: 通知公告
 * @date 2019/5/9 23:02
 */
public interface NoticeService {
    /**分页查询通知*/
    Page<SysNoticeDO> list(BaseQuery query, String startDate, String endDate, String noticeTitle);
    /**保存通知*/
    Result save(SysNoticeDO noticeDO);
    /**修改通知状态*/
    Result updateStatusById(Boolean noticeStatus,Long id);
    /**删除通知*/
    Result removeNotice(Long id);
    /**批量删除通知*/
    Result removeBatch(Long[] ids);
}
