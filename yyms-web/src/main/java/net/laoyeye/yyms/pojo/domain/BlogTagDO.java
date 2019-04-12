package net.laoyeye.yyms.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author laoyeye.net
 * @Description: 文章标签
 * @date 2019/4/2 21:50
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_tag",
        indexes = {@Index(name = "idx_tag_code", columnList = "tagCode", unique = true)})
@org.hibernate.annotations.Table(appliesTo = "blog_tag",comment="文章标签表")
public class BlogTagDO implements Serializable {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @NotBlank(message = "标签名称不能为空")
    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '标签名称'")
    private String tagName;

    @NotBlank(message = "标签编码不能为空")
    @Column(nullable = false, columnDefinition="varchar(50) COMMENT '标签编码'")
    private String tagCode;
}
