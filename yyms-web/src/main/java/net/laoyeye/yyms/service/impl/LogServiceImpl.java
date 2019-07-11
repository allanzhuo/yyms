package net.laoyeye.yyms.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.pojo.domain.SysLogDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.repository.SysLogRepository;
import net.laoyeye.yyms.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 日志管理
 * @author: zhangzhuo
 * @date: 2019/7/11 16:42
 */
@Slf4j
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public Page<SysLogDO> list(BaseQuery pageQuery, String userName, String operationDesc) {
        Pageable pageable = PageRequest.of(pageQuery.getPage()-1, pageQuery.getLimit(), Sort.Direction.DESC, "id");  //分页信息
        Specification<SysLogDO> spec = (Root<SysLogDO> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Path<String> name = root.get("userName");
            Path<String> operation = root.get("operation");
            List<Predicate> list = new ArrayList<Predicate>();
            if (StringUtils.isNotEmpty(userName)){
                list.add(cb.like(name, "%" + userName + "%"));
            }
            if (StringUtils.isNotEmpty(operationDesc)){
                list.add(cb.like(operation, "%" + operationDesc + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        return sysLogRepository.findAll(spec, pageable);
    }
}
