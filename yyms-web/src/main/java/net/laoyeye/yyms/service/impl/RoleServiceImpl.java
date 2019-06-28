package net.laoyeye.yyms.service.impl;

import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.repository.SysRoleRepository;
import net.laoyeye.yyms.service.RoleService;
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
 * @author laoyeye
 * @Description: 角色管理
 * @date 2019/6/27 22:08
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Override
    public Page<SysRoleDO> listByRoleName(BaseQuery pageQuery, String roleName) {
        Pageable pageable = PageRequest.of(pageQuery.getPage()-1, pageQuery.getLimit(), Sort.Direction.DESC, "id");  //分页信息
        Specification<SysRoleDO> spec = (Root<SysRoleDO> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Path<String> name = root.get("roleName");
            //方式二
            List<Predicate> list = new ArrayList<Predicate>();
            if (StringUtils.isNotEmpty(roleName)){
                list.add(cb.like(name, "%" + roleName + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        return sysRoleRepository.findAll(spec, pageable);
    }
}