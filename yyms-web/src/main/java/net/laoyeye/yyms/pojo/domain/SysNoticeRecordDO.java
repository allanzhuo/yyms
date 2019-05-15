package net.laoyeye.yyms.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static java.lang.Boolean.FALSE;

/**
 * @description: 通知公告发送记录
 * @author: laoyeye.net
 * @date: 2019/5/9 19:37
 */
@Data
@Builder(toBuilder=true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_notice_record")
@org.hibernate.annotations.Table(appliesTo = "sys_notice_record",comment="通知公告发送记录")
public class SysNoticeRecordDO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
    @Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
    private Long id;

    @NotNull(message = "通知ID不能为空")
    @Column(nullable = false, columnDefinition="bigint(20) COMMENT '通知ID'")
    private Long noticeId;

    @NotNull(message = "用户ID不能为空")
    @Column(nullable = false, columnDefinition="bigint(20) COMMENT '用户ID'")
    private Long userId;

    @Column(nullable = false, columnDefinition = "tinyint(1) COMMENT '是否已读'")
    @Builder.Default
    private Boolean readFlg = FALSE;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private Date readDate;
}
