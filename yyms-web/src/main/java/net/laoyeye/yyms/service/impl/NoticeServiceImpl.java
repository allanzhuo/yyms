package net.laoyeye.yyms.service.impl;

import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.domain.SysNoticeRecordDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.repository.SysNoticeRecordRepository;
import net.laoyeye.yyms.repository.SysNoticeRepository;
import net.laoyeye.yyms.repository.SysUserRepository;
import net.laoyeye.yyms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 通知公告
 * @author: laoyeye.net
 * @date: 2019/5/10 14:46
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private SysNoticeRepository sysNoticeRepository;
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private SysNoticeRecordRepository sysNoticeRecordRepository;

    @Override
    public Page<SysNoticeDO> list(BaseQuery baseQuery, String startDate, String endDate, String noticeTitle) {
        Pageable pageable = PageRequest.of(baseQuery.getPage()-1, baseQuery.getLimit(), Sort.Direction.DESC, "id");  //分页信息
        Specification<SysNoticeDO> spec = (Root<SysNoticeDO> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Path<String> title = root.get("noticeTitle");
            Path<LocalDateTime> createTime = root.get("createTime");
            //查询方式一：
//            Predicate p1 = cb.like(title, "%" + noticeTitle + "%");
//            Predicate p2 = cb.greaterThanOrEqualTo(createTime, LocalDate.parse(startDate));
//            Predicate p3 = cb.lessThanOrEqualTo(createTime, LocalDate.parse(endDate));
//            Predicate p = cb.and(p1, p2, p3);
//            return p;
            //方式二
            List<Predicate> list = new ArrayList<Predicate>();
            if (StringUtils.isNotEmpty(noticeTitle)){
                list.add(cb.like(title, "%" + noticeTitle + "%"));
            }
            if (startDate != null && startDate != "" && endDate != null && endDate != "") {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                list.add(cb.between(createTime, LocalDateTime.parse(startDate,df),LocalDateTime.parse(endDate,df)));
            }
//            if (endDate != null && endDate != "") {
//                list.add(cb.lessThanOrEqualTo(createTime, LocalDate.parse(endDate)));
//            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        return sysNoticeRepository.findAll(spec, pageable);
    }

    @Override
    public Result save(SysNoticeDO noticeDO) {
        //保存公告
        SysNoticeDO sysNoticeDO = sysNoticeRepository.save(noticeDO);
        List<SysUserDO> list = sysUserRepository.findAll();
        //保存发送记录
        for (SysUserDO sysUserDO : list) {
            SysNoticeRecordDO recordDO = SysNoticeRecordDO
                    .builder()
                    .noticeId(sysNoticeDO.getId())
                    .userId(sysUserDO.getId())
                    .build();
            sysNoticeRecordRepository.save(recordDO);
        }
        return Result.ok("新增通知成功！");
    }

}
