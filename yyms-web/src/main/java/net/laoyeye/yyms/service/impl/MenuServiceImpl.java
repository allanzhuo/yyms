package net.laoyeye.yyms.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.pojo.Result;
import net.laoyeye.yyms.pojo.domain.SysMenuDO;
import net.laoyeye.yyms.pojo.vo.SysMenuVO;
import net.laoyeye.yyms.repository.SysMenuRepository;
import net.laoyeye.yyms.repository.SysRoleMenuRepository;
import net.laoyeye.yyms.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author laoyeye
 * @Description: 后台菜单service
 * @date 2019/4/29 23:09
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Override
    public List<SysMenuDO> listMenus() {
        return sysMenuRepository.findAllByOrderBySortAsc();
    }

    @Override
    public Result updateStatusById(Boolean status, Long id) {
        Optional<SysMenuDO> menuDO = sysMenuRepository.findById(id);
        if (menuDO.isPresent()){
            SysMenuDO menu = menuDO.get().toBuilder()
                    .status(status)
                    .build();
            SysMenuDO newMenu = sysMenuRepository.save(menu);
        }
        return Result.ok("修改菜单状态成功！");
    }

    @Override
    public Result saveMenu(SysMenuDO menuDO) {
        sysMenuRepository.save(menuDO);
        return Result.ok("新增菜单成功！");
    }

    @Override
    public Result updateMenu(SysMenuDO menuDO) {
        sysMenuRepository.save(menuDO);
        return Result.ok("修改菜单成功！");
    }

    @Override
    public SysMenuDO getMenuById(Long id) {
        SysMenuDO menuDO = sysMenuRepository.findById(id).orElse(new SysMenuDO());
        return menuDO;
    }

    @Override
    public Result removeMenu(Long[] ids) {
        int i = sysMenuRepository.deleteBatch(ids);
        return Result.ok("删除选中的【" + i + "】条数据成功！");
    }

    @Override
    public Result listInitMenus(String roleCode) {
        //查询父菜单为0的所有菜单
        List<SysMenuDO> list = sysMenuRepository.findByPidOrderBySortAsc(0L,roleCode);
        List<SysMenuVO> listVO = list.stream().map(menuDO -> {
            SysMenuVO menuVO = SysMenuVO.builder()
                    .id(menuDO.getId())
                    .pid(menuDO.getPid())
                    .title(menuDO.getTitle())
                    .type(menuDO.getType())
                    .icon(menuDO.getIcon())
                    .url(menuDO.getUrl())
                    .build();
            return menuVO;
        }).collect(toList());
        for (SysMenuVO menuVO : listVO) {
            //查询一级菜单
            List<SysMenuDO> listChildren = sysMenuRepository.findByPidOrderBySortAsc(menuVO.getId(),roleCode);
            List<SysMenuVO> listChildrenVO = listChildren.stream().map(menuDO -> {
                SysMenuVO menuChildrenVO = SysMenuVO.builder()
                        .id(menuDO.getId())
                        .pid(menuDO.getPid())
                        .title(menuDO.getTitle())
                        .type(menuDO.getType())
                        .icon(menuDO.getIcon())
                        .url(menuDO.getUrl())
                        .build();
                return menuChildrenVO;
            }).collect(toList());
            menuVO.setChildren(listChildrenVO);
        }

        return Result.ok(listVO,"初始化菜单成功！");
    }

    @Override
    public Result listGrantMenus(String roleCode) {
        //查询所有已拥有权限的菜单ID
        List<String> menuIds = sysRoleMenuRepository.findMenuIdByRoleCode(roleCode);
        List<SysMenuDO> list = sysMenuRepository.findByPidOrderBySortAsc(0L);
        List<SysMenuVO> listVO = new ArrayList<>();
        for (SysMenuDO menuDO : list) {
            SysMenuVO menuVO = SysMenuVO.builder()
                    .id(menuDO.getId())
                    .pid(menuDO.getPid())
                    .title(menuDO.getTitle())
                    .type(menuDO.getType())
                    .icon(menuDO.getIcon())
                    .url(menuDO.getUrl())
                    .build();
            if (menuIds != null && menuIds.contains(menuVO.getId())) {
                menuVO.setChecked(Boolean.TRUE);
            }
            listVO.add(menuVO);
        }
        for (SysMenuVO menuVO : listVO) {
            List<SysMenuDO> listChildren = sysMenuRepository.findByPidOrderBySortAsc(menuVO.getId());
            List<SysMenuVO> listChildrenVO = listChildren.stream().map(menuDO -> {
                SysMenuVO menuChildrenVO = SysMenuVO.builder()
                        .id(menuDO.getId())
                        .pid(menuDO.getPid())
                        .title(menuDO.getTitle())
                        .type(menuDO.getType())
                        .icon(menuDO.getIcon())
                        .url(menuDO.getUrl())
                        .build();
                if (menuIds != null && menuIds.contains(menuChildrenVO.getId())){
                    menuChildrenVO.setChecked(Boolean.TRUE);
                }
                return menuChildrenVO;
            }).collect(toList());
            menuVO.setChildren(listChildrenVO);
        }
        return Result.ok(listVO,"授权菜单初始化成功！");
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = sysMenuRepository.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
