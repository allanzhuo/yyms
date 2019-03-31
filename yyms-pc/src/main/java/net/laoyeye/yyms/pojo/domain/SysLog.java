package net.laoyeye.yyms.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

/**
 * @author laoyeye
 * @Description: 系统日志表
 * @date 2019/3/31 17:51
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_log")
@org.hibernate.annotations.Table(appliesTo = "sys_log",comment="系统日志表")
public class SysLog {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @Column(columnDefinition="bigint(20) COMMENT '用户ID'")
    private String userId;

    @Column(columnDefinition="varchar(255) COMMENT '用户操作'")
    private String operation;

    @Column(columnDefinition="int(11) COMMENT '响应时间'")
    private int responseTime;

    @Column(columnDefinition="varchar(50) COMMENT '请求方法'")
    private String requestMethod;

    @Column(columnDefinition="varchar(5000) COMMENT '请求参数'")
    private String params;

    @Column(columnDefinition="varchar(64) COMMENT 'IP地址'")
    private String ip;

    @CreationTimestamp
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime = now();

}
