package net.laoyeye.yyms.service.impl;

import java.util.Optional;
import net.laoyeye.yyms.pojo.domain.document.TestDO;
import net.laoyeye.yyms.repository.es.TestRepository;
import net.laoyeye.yyms.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description :   .
 *
 * @author : zhangzhuo
 * @date : Created in 2020/7/12 16:02
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    public TestDO queryTest(String id) {
        Optional<TestDO> byId = testRepository.findById(id);
        return byId.get();
    }

    @Override
    public TestDO queryCondition() {
        return null;
    }
}
