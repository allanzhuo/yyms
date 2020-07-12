package net.laoyeye.yyms.repository.es;

import net.laoyeye.yyms.pojo.domain.document.TestDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description :   .
 *
 * @author : zhangzhuo
 * @date : Created in 2020/7/12 15:51
 */
public interface TestRepository  extends ElasticsearchRepository<TestDO, String> {

}
