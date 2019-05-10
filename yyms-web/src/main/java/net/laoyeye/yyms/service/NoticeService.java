package net.laoyeye.yyms.service;

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
}
