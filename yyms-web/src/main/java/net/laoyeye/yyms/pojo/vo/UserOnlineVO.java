package net.laoyeye.yyms.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * @author laoyeye
 * @Description: 在线用户
 * @date 2019/7/13 11:28
 */
@Getter
@Setter
public class UserOnlineVO {
    /**sessionId*/
    private String sessionId;
    /** 用户名*/
    private String userName;
    /** ip地址*/
    private String ip;
    /**在线状态*/
    private Boolean status = Boolean.TRUE;
    /**session创建时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTimestamp;
    /**session最后访问时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastAccessTime;
    /**超时时间*/
    private Long timeout;
}
