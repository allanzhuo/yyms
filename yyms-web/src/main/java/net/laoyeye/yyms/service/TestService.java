package net.laoyeye.yyms.service;

import net.laoyeye.yyms.pojo.domain.document.TestDO;

/**
 * Description :   .
 *
 * @author : zhangzhuo
 * @date : Created in 2020/7/12 16:02
 */
public interface TestService {

    TestDO queryTest(String id);

    TestDO queryCondition();

}
