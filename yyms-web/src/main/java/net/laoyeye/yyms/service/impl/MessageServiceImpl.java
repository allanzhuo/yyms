package net.laoyeye.yyms.service.impl;

import net.laoyeye.enums.ResultEnum;
import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.domain.SysNoticeRecordDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.repository.SysNoticeRecordRepository;
import net.laoyeye.yyms.repository.SysNoticeRepository;
import net.laoyeye.yyms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author laoyeye
 * @Description: 消息中心
 * @date 2019/6/4 0:23
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private SysNoticeRecordRepository sysNoticeRecordRepository;
    @Autowired
    private SysNoticeRepository sysNoticeRepository;

    @Override
    public Result list(BaseQuery query,Long userId) {
        List<Map<String, Object>> list = sysNoticeRecordRepository.findMessageCenter(userId,(query.getPage() - 1) * query.getLimit(), query.getLimit());
        Result result = new Result(list,
                sysNoticeRecordRepository.findMessageCenterNum(userId));
        return result;
    }

    @Override
    public Result getMessageDetail(Long recordId, Long noticeId) {
        Optional<SysNoticeDO> noticeDO = sysNoticeRepository.findById(noticeId);
        SysNoticeDO sysNoticeDO = noticeDO.orElse(new SysNoticeDO());
        Optional<SysNoticeRecordDO> recordDO = sysNoticeRecordRepository.findById(recordId);
        if (recordDO.isPresent() && Boolean.FALSE.equals(recordDO.get().getReadFlg())) {
            SysNoticeRecordDO sysNoticeRecordDO = recordDO.get()
                    .toBuilder()
                    .readFlg(Boolean.TRUE)
                    .readDate(new Date())
                    .build();
            SysNoticeRecordDO save = sysNoticeRecordRepository.save(sysNoticeRecordDO);
        } else if (!recordDO.isPresent()) {
            return Result.build(ResultEnum.NOT_FOUND.getCode(), "通知不存在，请检查操作！");
        }
        ;
        List list = new ArrayList<>();
        list.add(sysNoticeDO);
        return new Result(list);
    }

    @Override
    public Result updateReadAll(Long userId) {
        List<SysNoticeRecordDO> list = sysNoticeRecordRepository.findAllByReadFlgAndUserId(Boolean.FALSE, userId);
        for (SysNoticeRecordDO recordDO : list) {
            SysNoticeRecordDO sysNoticeRecordDO = recordDO.toBuilder()
                    .readFlg(Boolean.TRUE)
                    .readDate(new Date())
                    .build();
            SysNoticeRecordDO save = sysNoticeRecordRepository.save(sysNoticeRecordDO);
        }
        return Result.ok("通知：全部已读！");
    }

    @Override
    public Result updateReadByIds(Long[] ids,Long userId) {
        sysNoticeRecordRepository.updateReadBatch(ids);
        List<SysNoticeRecordDO> list = sysNoticeRecordRepository.findAllByReadFlgAndUserId(Boolean.FALSE, userId);
        return Result.ok(list,"标记已读成功！");
    }

    @Override
    public Result removeBatch(Long[] ids,Long userId) {
        sysNoticeRecordRepository.deleteBatchByIds(ids);
        List<SysNoticeRecordDO> list = sysNoticeRecordRepository.findAllByReadFlgAndUserId(Boolean.FALSE, userId);
        return  Result.ok(list,"删除成功！");
    }

    @Override
    public Integer countUnread(Long userId) {
        List<SysNoticeRecordDO> list = sysNoticeRecordRepository.findAllByReadFlgAndUserId(Boolean.FALSE, userId);
        return list.size();
    }
}
