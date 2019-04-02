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
 * @Description: 文章分类
 * @date 2019/4/2 21:37
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_category",
        indexes = {@Index(name = "idx_cate_code", columnList = "cateCode", unique = true)})
@org.hibernate.annotations.Table(appliesTo = "blog_category",comment="文章分类表")
public class BlogCategoryDO implements Serializable {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '分类名称'")
    private String cateName;

    @NotBlank(message = "分类编码不能为空")
    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '分类编码'")
    private String cateCode;

    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '分类描述'")
    private String cateDesc;
}
