package net.laoyeye.yyms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;
import static java.time.LocalDateTime.now;

/**
 * @description: 角色表
 * @author: laoyeye.net
 * @date: 2019/3/27 10:53
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_role",
        indexes = {@Index(name = "uniq_role_code", columnList = "roleCode", unique = true)})
@org.hibernate.annotations.Table(appliesTo = "sys_role",comment="角色表")
public class SysRoleDO {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="varchar(50) COMMENT '角色名称'")
    private String roleName;

    @Column(columnDefinition="varchar(50) COMMENT '角色标识'")
    private String roleCode;

    @Column(columnDefinition="varchar(100) COMMENT '备注'")
    private String remark;

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
