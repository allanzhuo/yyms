package net.laoyeye.yyms.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author laoyeye.net
 * @Description: 用户角色表
 * @date 2019/3/31 17:11
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user_role")
@org.hibernate.annotations.Table(appliesTo = "sys_user_role",comment="用户角色表")
public class SysUserRoleDO implements Serializable {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="bigint(20) COMMENT '用户ID'")
    private String userId;

    @Column(columnDefinition="varchar(50) COMMENT '角色标识'")
    private String roleCode;
}
