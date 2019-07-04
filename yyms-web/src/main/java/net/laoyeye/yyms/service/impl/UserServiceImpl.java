package net.laoyeye.yyms.service.impl;

import net.laoyeye.enums.ResultEnum;
import net.laoyeye.pojo.Result;
import net.laoyeye.utils.StringUtils;
import net.laoyeye.yyms.pojo.domain.SysRoleDO;
import net.laoyeye.yyms.pojo.domain.SysRoleMenuDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.pojo.query.BaseQuery;
import net.laoyeye.yyms.repository.SysUserRepository;
import net.laoyeye.yyms.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author laoyeye
 * @Description: 用户信息service
 * @date 2019/6/9 19:30
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public Result updatePassword(String oldPassword,String password,String repassword,Long userId) {
        Optional<SysUserDO> user = sysUserRepository.findById(userId);
        SysUserDO userDO = user.get();
        if (!userDO.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))){
            return Result.build(ResultEnum.NOT_FOUND.getCode(),"当前密码错误，请检查！");
        }
        if (password.equals(repassword)) {
            userDO.setUpdateUser(userDO.getUserName());
            if (!StringUtils.isEmpty(password)) {
                userDO.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            }
            sysUserRepository.save(userDO);
            return Result.ok("密码修改成功，请重新登录！");
        }
        return Result.build(500, "密码验证不一致，请检查！");
    }

    @Override
    public Result updateAccount(SysUserDO userDO) {
        Optional<SysUserDO> sysUserDO = sysUserRepository.findById(userDO.getId());
        SysUserDO user = sysUserDO.get();
        SysUserDO newUser = user.toBuilder()
                .userName(userDO.getUserName())
                .nickName(userDO.getNickName())
                .avatar(userDO.getAvatar())
                .email(userDO.getEmail())
                .profile(userDO.getProfile())
                .build();
        sysUserRepository.save(newUser);
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        //修改属性
        String realmName = principalCollection.getRealmNames().iterator().next();
        newUser.setPassword(null);
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(newUser, realmName);
        //重新加载Principal
        SecurityUtils.getSubject().runAs(newPrincipalCollection);
        return Result.ok("设置资料成功！");
    }

    @Override
    public Page<SysUserDO> listByNickName(BaseQuery pageQuery, String nickName) {
        Pageable pageable = PageRequest.of(pageQuery.getPage()-1, pageQuery.getLimit(), Sort.Direction.DESC, "id");  //分页信息
        Specification<SysUserDO> spec = (Root<SysUserDO> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Path<String> name = root.get("nickName");
            //方式二
            List<Predicate> list = new ArrayList<Predicate>();
            if (StringUtils.isNotEmpty(nickName)){
                list.add(cb.like(name, "%" + nickName + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        };
        return sysUserRepository.findAll(spec, pageable);
    }

    @Override
    public SysUserDO getUserById(Long id) {
        Optional<SysUserDO> sysUserDO = sysUserRepository.findById(id);
        SysUserDO userDO = sysUserDO.orElse(new SysUserDO());
        return userDO;
    }

    @Override
    public Result updateStatusById(Boolean status, Long id) {
        Optional<SysUserDO> userDO = sysUserRepository.findById(id);
        if (userDO.isPresent()){
            SysUserDO role = userDO.get().toBuilder()
                    .status(status)
                    .build();
            sysUserRepository.save(role);
        }
        return Result.ok("修改用户状态成功！");
    }

    @Override
    public Result remove(Long id) {
        sysUserRepository.deleteById(id);
        return Result.ok("删除用户成功！");
    }

    @Override
    public Result removeBatch(Long[] ids) {
        int i = sysUserRepository.deleteBatch(ids);
        return Result.ok("删除选中的【" + i + "】条数据成功！");
    }

    @Override
    public Result saveOrUpdateRole(SysUserDO userDO) {
        SysUserDO user = null;
        if (userDO.getId() != null){
            Optional<SysUserDO> sysUserDO = sysUserRepository.findById(userDO.getId());
            user = sysUserDO.get()
                    .toBuilder()
                    .nickName(userDO.getNickName())
                    .email(userDO.getEmail())
                    .roleCode(userDO.getRoleCode())
                    .build();
        } else {
            String password = userDO.getUserName()+"123456";
            String pass = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(password.getBytes()).getBytes());
            user = userDO.toBuilder()
                    .password(pass)
                    .avatar("https://images.laoyeye.net/head.png")
                    .build();
        }
        sysUserRepository.save(user);
        return Result.ok("编辑用户成功！");
    }
}
