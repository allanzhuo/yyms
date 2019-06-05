package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.query.BaseQuery;

/**
 * @author laoyeye
 * @Description: 消息中心
 * @date 2019/6/4 0:23
 */
public interface MessageService {
    /**分页查询消息*/
    Result list(BaseQuery query);
    /**查询消息详情*/
    Result getMessageDetail(Long recordId, Long noticeId);
}
