package net.laoyeye.yyms.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author laoyeye
 * @Description: 消息中心VO
 * @date 2019/6/4 0:13
 */
@Getter
@Setter
public class MessageCenterVO {
    /**通知标题*/
    private String noticeTitle;
    /**通知时间*/
    private Date createTime;
    /**阅读时间*/
    private Boolean readDate;
}
