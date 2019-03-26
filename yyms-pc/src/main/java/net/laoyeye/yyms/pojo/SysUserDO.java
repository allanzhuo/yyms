package net.laoyeye.yyms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;
import static java.time.LocalDateTime.now;

/**
 * @author laoyeye
 * @Description: user实体类
 * @date 2019/3/25 21:48
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "sys_user")
public class SysUserDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, length = 11)
    private Long id;

    @Column(length = 20)
    private String username;

    @Column(length = 50)
    private String nickname;

    @Column(length = 100)
    private String avatar;

    private String password;

    @Column(updatable = false, name = "[create]")
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create = now();

    @Column(length = 20)
    private String qqNum;

    private String email;

    /**
     * 默认为普通访客用户
     * 一个用户可能有多个角色，此字段为默认角色
     */
    @Column(nullable = false, length = 11)
    @Builder.Default
    private Long defaultRoleId = 2L;

    @Column(nullable = false, length = 1, columnDefinition = "tinyint(1)")
    @Builder.Default
    private Boolean enable = TRUE;

    private String qqOpenId;

    private String wechatOpenId;
}