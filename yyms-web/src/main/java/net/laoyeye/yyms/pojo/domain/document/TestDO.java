package net.laoyeye.yyms.pojo.domain.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description :   .
 *
 * @author : zhangzhuo
 * @date : Created in 2020/7/12 15:45
 */
@Data
@Document(indexName = "laoyeye", type = "user")
public class TestDO {

    @Id
    private String id;

    private String name;

    private String sex;

    private String adress;

}
