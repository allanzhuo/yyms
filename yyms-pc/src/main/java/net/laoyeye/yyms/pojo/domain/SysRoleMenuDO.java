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
 * @Description: 角色菜单表
 * @date 2019/3/31 17:11
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_role_menu")
@org.hibernate.annotations.Table(appliesTo = "sys_role_menu",comment="角色菜单表")
public class SysRoleMenuDO implements Serializable {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="varchar(50) COMMENT '角色标识'")
    private String roleCode;

    @Column(columnDefinition="bigint(20) COMMENT '菜单ID'")
    private String menuId;
}
