package net.laoyeye.yyms.pojo.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author laoyeye.net
 * @Description: 系统配置表
 * @date 2019/4/2 22:16
 */
public class SysOptions {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;
}
