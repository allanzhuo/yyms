package net.laoyeye.yyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author laoyeye.net
 * @Description: 启动入口
 * @date 2019/3/25 21:56
 */
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@EnableTransactionManagement
@EnableCaching
public class AppStart {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppStart.class, args);
    }
}

