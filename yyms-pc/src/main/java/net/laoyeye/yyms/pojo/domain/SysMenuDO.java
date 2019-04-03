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
import static java.lang.Boolean.TRUE;
import static java.time.LocalDateTime.now;

/**
 * @author laoyeye.net
 * @Description: 菜单表
 * @date 2019/3/31 19:17
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_menu")
@org.hibernate.annotations.Table(appliesTo = "sys_menu",comment="菜单表")
public class SysMenuDO implements Serializable {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="bigint(20) COMMENT '父菜单ID'")
    private Long pid;

    @Column(columnDefinition="varchar(255) COMMENT '菜单名称'")
    private String title;

    @Column(columnDefinition="int(1) COMMENT '菜单类型'")
    private int type;

    @Column(columnDefinition="varchar(50) COMMENT '菜单图标类型'")
    private String font;

    @Column(columnDefinition="varchar(50) COMMENT '菜单图标'")
    private String icon;

    @Column(columnDefinition="varchar(255) COMMENT '菜单URL'")
    private String url;

    @Column(columnDefinition="varchar(255) COMMENT '权限标识'")
    private String perms;

    @Column(columnDefinition="int(11) COMMENT '排序'")
    private int sort;

    @Column(columnDefinition="tinyint(1) COMMENT '是否展开'")
    @Builder.Default
    private boolean spread = FALSE;

    @Column(columnDefinition="tinyint(1) COMMENT '是否有子菜单'")
    @Builder.Default
    private boolean children = FALSE;

    @Column(columnDefinition="varchar(255) COMMENT '备注'")
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
