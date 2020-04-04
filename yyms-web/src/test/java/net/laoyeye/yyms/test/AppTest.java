package net.laoyeye.yyms.test;

import java.util.List;
import java.util.Optional;
import net.laoyeye.yyms.AppStart;
import net.laoyeye.yyms.pojo.domain.SysLogDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.repository.SysLogRepository;
import net.laoyeye.yyms.repository.SysMenuRepository;
import net.laoyeye.yyms.repository.SysUserRepository;
import net.laoyeye.yyms.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: 测试类
 * @author: laoyeye
 * @date: 2019/3/26 18:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStart.class)
public class AppTest {
    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private MenuService menuService;

    @Autowired
    private SysLogRepository sysLogRepository;

    @Test
    public void hello() {
        System.out.println("hello world");
        List<SysUserDO> all = userRepository.findAll();
        System.out.println(all);
        SysUserDO user = SysUserDO.builder()
                .nickName("ceshi")
                .email("bgood@sina.com")
                .createUser("system")
                .updateUser("system")
                .build();
        SysUserDO save = userRepository.save(user);

        List<SysUserDO> all2 = userRepository.findAll();
        System.out.println(all2);
        Optional<SysUserDO> byId = userRepository.findById(560784148479086592L);
        SysUserDO sysUserDO = byId.get();
        sysUserDO.toBuilder()
                .updateUser("women")
                .build();
        SysUserDO sysUserDO1 = userRepository.saveAndFlush(sysUserDO);
        System.out.println(sysUserDO1);
        SysUserDO user2 = new SysUserDO();
        user2.setCreateUser("sy");
        user2.setUpdateUser("sy");
        userRepository.save(user2);
    }

    @Test
    public void test() {
        Optional<SysUserDO> byId = userRepository.findById(560784148479086592L);
        SysUserDO sysUserDO = byId.get();
        SysUserDO women = sysUserDO.toBuilder()
                .updateUser("wo8tt0")
                .build();
        SysUserDO sysUserDO1 = userRepository.saveAndFlush(women);
        System.out.println(sysUserDO1);
    }

    @Test
    public void testUser() {

        //
//        List<SysMenuDO> all = sysMenuRepository.findAllByOrderBySortAsc();
//        System.out.println(all);
//        Result result = menuService.listInitMenus();
//        System.out.println(result);

        Optional<SysLogDO> optional  = sysLogRepository.findById(444271380247588864L);
        System.out.println(optional.get());


        List<SysLogDO> list = sysLogRepository.findByUserName("zhangzhuo");
        System.out.println(list);

    }
}
