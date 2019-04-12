package net.laoyeye.yyms.test;

import net.laoyeye.yyms.AppStart;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @description: 测试类
 * @author: laoyeye
 * @date: 2019/3/26 18:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStart.class)
public class AppTest {
    @Autowired
    private UserRepository userRepository;

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
}
