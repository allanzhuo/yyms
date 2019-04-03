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
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;
import static java.time.LocalDateTime.now;

/**
 * @description: 点赞收藏表
 * @author: laoyeye.net
 * @date: 2019/4/3 19:10
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog_vote")
@org.hibernate.annotations.Table(appliesTo = "blog_vote",comment="点赞收藏表")
public class BlogVoteDO implements Serializable {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @NotBlank(message = "用户ID不能为空")
    @Column(nullable = false, columnDefinition="bigint(20) COMMENT '用户ID'")
    private Long userId;

    @NotBlank(message = "文章ID不能为空")
    @Column(nullable = false, columnDefinition="bigint(20) COMMENT '文章ID'")
    private Long postId;

    @Column(nullable = false, columnDefinition="tinyint(1) COMMENT '是否收藏'")
    @Builder.Default
    private Boolean voteStatus = TRUE;

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
