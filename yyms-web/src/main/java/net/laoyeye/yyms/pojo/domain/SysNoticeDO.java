package net.laoyeye.yyms.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @description: 通知公告表
 * @author: laoyeye.net
 * @date: 2019/5/9 19:10
 */
@Data
@Builder(toBuilder=true)
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_notice")
@org.hibernate.annotations.Table(appliesTo = "sys_notice",comment="通知公告表")
public class SysNoticeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "net.laoyeye.yyms.pojo.strategy.IdentifierGeneratorImpl")
	@Column(updatable = false, nullable = false, columnDefinition="bigint COMMENT '主键'")
	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;

	@Column(nullable = false, columnDefinition="varchar(20) COMMENT '标题'")
	private String noticeTitle;

	@Column(nullable = false, columnDefinition="text COMMENT '内容'")
	private String noticeContent;

	@Column(nullable = false, columnDefinition = "tinyint(1) COMMENT '是否有效'")
	@Builder.Default
	private Boolean noticeStatus = TRUE;

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
