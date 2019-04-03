package net.laoyeye.yyms.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @description: 友情链接
 * @author: laoyeye.net
 * @date: 2019/4/3 23:27
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_link")
@org.hibernate.annotations.Table(appliesTo = "blog_link",comment="友情链接表")
public class BlogLink implements Serializable{

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @NotEmpty(message = "友情链接名称不能为空！")
    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '链接名称'")
    private String linkName;

    @NotEmpty(message = "友情链接地址不能为空！")
    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '链接地址'")
    private String linkUrl;

    @Column(columnDefinition="varchar(255) COMMENT '链接图片'")
    private String linkImage;

    @Column(columnDefinition="varchar(255) COMMENT '链接备注'")
    private String remark;
}
