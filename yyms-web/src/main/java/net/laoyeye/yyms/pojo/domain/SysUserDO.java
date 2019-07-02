package net.laoyeye.yyms.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;
import static java.time.LocalDateTime.now;

/**
 * @author laoyeye.net
 * @Description: user实体类
 * @date 2019/3/25 21:48
 */
@Data
@Builder(toBuilder=true)
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user",
        uniqueConstraints={@UniqueConstraint(name = "uniq_user_name", columnNames="userName")},
        indexes = {@Index(name = "uniq_qq_open_id", columnList = "qqOpenId", unique = true),
                @Index(name = "uniq_wx_open_id", columnList = "wxOpenId", unique = true)})
@org.hibernate.annotations.Table(appliesTo = "sys_user",comment="用户表")
public class SysUserDO implements Serializable {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="varchar(20) COMMENT '用户名'")
    private String userName;

    @Column(columnDefinition="varchar(50) COMMENT '昵称'")
    private String nickName;

    @Column(columnDefinition="varchar(50) COMMENT '密码'")
    private String password;

    @Column(columnDefinition="varchar(255) COMMENT '头像'")
    private String avatar;

    @Column(columnDefinition="varchar(100) COMMENT '邮箱'")
    private String email;

    @Column(columnDefinition="varchar(255) COMMENT '个人简介'")
    private String profile;

    @Column(columnDefinition="varchar(50) COMMENT '角色编码'")
    private String roleCode;

    @Column(columnDefinition = "varchar(32) COMMENT 'qqOpenId'")
    private String qqOpenId;

    @Column(columnDefinition = "varchar(32) COMMENT 'wxOpenId'")
    private String wxOpenId;

    @Column(nullable = false, columnDefinition = "tinyint(1) COMMENT '是否有效'")
    @Builder.Default
    private Boolean status = TRUE;

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