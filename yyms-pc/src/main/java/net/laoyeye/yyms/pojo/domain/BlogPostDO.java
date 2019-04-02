package net.laoyeye.yyms.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;
import static java.time.LocalDateTime.now;

/**
 * @author laoyeye.net
 * @Description: 文章随笔
 * @date 2019/4/1 21:47
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_post",
        indexes = {@Index(name = "idx_cate_code", columnList = "cateCode")})
@org.hibernate.annotations.Table(appliesTo = "blog_post",comment="文章随笔表")
public class BlogPostDO implements Serializable {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="varchar(255) COMMENT '分类Code'")
    private String cateCode;

    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '标题'")
    private String postTitle;

    @Column(columnDefinition="varchar(255) COMMENT '封面'")
    private String postCover;

    @Column(nullable = false, columnDefinition="varchar(255) COMMENT '摘要'")
    private String postSummary;

    @Column(nullable = false, columnDefinition="text COMMENT '文章内容'")
    private String postContent;

    @Column(nullable = false, columnDefinition="bigint(20) COMMENT '访问量'")
    @Builder.Default
    private Long postViews = 0L;

    @Column(nullable = false, columnDefinition="int(1) COMMENT '0:回收站 1：草稿 2：已发布'")
    @Builder.Default
    private Integer postStatus = 2;

    @Column(columnDefinition="varchar(255) COMMENT '自定义URL'")
    private String postUrl;

    @Column(nullable = false, columnDefinition="tinyint(1) COMMENT '是否访问链接'")
    @Builder.Default
    private Boolean accessLink = FALSE;

    @Column(nullable = false, columnDefinition="tinyint(1) COMMENT '是否置顶'")
    @Builder.Default
    private Boolean postTop = FALSE;

    @Column(nullable = false, columnDefinition="tinyint(1) COMMENT '是否允许评论'")
    @Builder.Default
    private Boolean allowComment = FALSE;

    @Column(nullable = false, columnDefinition="varchar(20) COMMENT '文章密码'")
    private String postPassword;

    @Column(nullable = false, columnDefinition="bigint(20) COMMENT '作者ID'")
    private Long authorId;

    @Column(updatable = false, nullable = false, length = 20)
    private String createUser;

    @CreationTimestamp
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime = now();

    @Column(nullable = false, length = 20)
    private String updateUser;

    @UpdateTimestamp
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime updateTime = now();

}
