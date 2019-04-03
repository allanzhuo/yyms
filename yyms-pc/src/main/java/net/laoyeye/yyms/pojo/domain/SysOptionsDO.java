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
 * @Description: 系统配置表
 * @date 2019/4/2 22:16
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_options")
@org.hibernate.annotations.Table(appliesTo = "sys_options",comment="系统配置表")
public class SysOptionsDO implements Serializable{
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="varchar(50) COMMENT '配置code'")
    private String optionCode;

    @Column(columnDefinition="varchar(255) COMMENT '配置value'")
    private String optionValue;

    @Column(columnDefinition="varchar(255) COMMENT '备注'")
    private String remark;
}
