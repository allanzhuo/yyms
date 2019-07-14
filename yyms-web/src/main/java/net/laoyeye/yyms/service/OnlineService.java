package net.laoyeye.yyms.service;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.query.BaseQuery;

/**
 * @author laoyeye
 * @Description: 在线用户
 * @date 2019/7/13 11:22
 */
public interface OnlineService {
    /**分页查询在线用户*/
    Result list(BaseQuery query, String username);
    /**强制下线用户*/
    Boolean removeUser(String sessionId);
}
