package net.laoyeye.yyms.test;

import net.laoyeye.yyms.AppStart;
import net.laoyeye.yyms.pojo.SysUserDO;
import net.laoyeye.yyms.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description: 测试类
 * @author: zhangzhuo
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
    }
}
