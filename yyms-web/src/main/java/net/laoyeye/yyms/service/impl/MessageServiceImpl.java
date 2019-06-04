package net.laoyeye.yyms.service.impl;

import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.repository.SysNoticeRecordRepository;
import net.laoyeye.yyms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laoyeye
 * @Description: 消息中心
 * @date 2019/6/4 0:23
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private SysNoticeRecordRepository sysNoticeRecordRepository;

    @Override
    public Result list(BaseQuery query) {
        Result result = new Result(sysNoticeRecordRepository.findMessageCenter((query.getPage()-1)*query.getLimit(),query.getLimit()),
                sysNoticeRecordRepository.findMessageCenterNum());
        return result;
    }
}
