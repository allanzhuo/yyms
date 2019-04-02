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
 * @Description: 文章标签关联表
 * @date 2019/4/2 21:55
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_post_tag")
@org.hibernate.annotations.Table(appliesTo = "blog_post_tag",comment="文章标签关联表")
public class BlogPostTagDO implements Serializable {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @NotBlank(message = "文章ID不能为空")
    @Column(nullable = false, columnDefinition="bigint(20) COMMENT '文章ID'")
    private Long postId;

    @NotBlank(message = "标签编码不能为空")
    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '标签编码'")
    private String tagCode;
}
