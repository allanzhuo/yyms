package net.laoyeye.yyms.service.impl;

import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.pojo.vo.UserOnlineVO;
import net.laoyeye.yyms.service.OnlineService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author laoyeye
 * @Description: 在线用户
 * @date 2019/7/13 11:22
 */
@Service
public class OnlineServiceImpl implements OnlineService{
    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public Result list(BaseQuery query, String userName) {
        List<UserOnlineVO> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            UserOnlineVO userOnline = new UserOnlineVO();
            if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                continue;
            } else {
                SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session
                        .getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                SysUserDO userDO = (SysUserDO) principalCollection.getPrimaryPrincipal();
                userOnline.setUserName(userDO.getUserName());
            }
            userOnline.setSessionId((String) session.getId());
            userOnline.setIp(session.getHost());
            userOnline.setStartTimestamp(session.getStartTimestamp());
            userOnline.setLastAccessTime(session.getLastAccessTime());
            userOnline.setTimeout(session.getTimeout());
            if (StringUtils.isNotEmpty(userName)) {
                if (userOnline.getUserName().equals(userName)) {
                    list.add(userOnline);
                }
            } else {
                list.add(userOnline);
            }
        }
        int size = list.size();
        int start = (query.getPage() - 1) * query.getLimit();
        List<UserOnlineVO> listPage = list.subList(start , size-start > query.getLimit() ? start + query.getLimit() : size);
        //创建一个返回值对象
        return new Result(listPage, (long)size);
    }

    @Override
    public Boolean removeUser(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        session.setTimeout(0);
        return true;
    }
}
