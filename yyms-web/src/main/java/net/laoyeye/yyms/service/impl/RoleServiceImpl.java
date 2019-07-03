package net.laoyeye.yyms.service.impl;

import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.pojo.domain.SysNoticeDO;
import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.domain.SysRoleMenuDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.repository.SysRoleMenuRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author laoyeye
 * @Description: 角色管理
 * @date 2019/6/27 22:08
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

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

    @Override
    public Result updateStatusById(Boolean roleStatus, Long id) {
        Optional<SysRoleDO> roleDO = sysRoleRepository.findById(id);
        if (roleDO.isPresent()){
            SysRoleDO role = roleDO.get().toBuilder()
                    .roleStatus(roleStatus)
                    .build();
            sysRoleRepository.save(role);
        }
        return Result.ok("修改角色状态成功！");
    }

    @Override
    public Result removeBatch(Long[] ids) {
        int i = sysRoleRepository.deleteBatch(ids);
        return Result.ok("删除选中的【" + i + "】条数据成功！");
    }

    @Override
    public Result removeRole(Long id) {
        sysRoleRepository.deleteById(id);
        return Result.ok("删除角色成功！");
    }

    @Override
    public SysRoleDO getRole(Long id) {

        return sysRoleRepository.findById(id).get();
    }

    @Override
    public List<SysRoleDO> listRoleByRoleStatus(Boolean roleStatus) {
        return sysRoleRepository.findAllByRoleStatus(roleStatus);
    }

    @Override
    public Result saveOrUpdateRole(SysRoleDO roleDO, Long[] menuIds) {
        SysRoleDO role = sysRoleRepository.save(roleDO);
        sysRoleMenuRepository.deleteByRoleCode(role.getRoleCode());
        for (Long menuId : menuIds) {
            SysRoleMenuDO roleMenuDO = new SysRoleMenuDO();
            roleMenuDO.setMenuId(menuId);
            roleMenuDO.setRoleCode(role.getRoleCode());
            sysRoleMenuRepository.save(roleMenuDO);
        }
        return Result.ok("编辑角色成功！");
    }
}
