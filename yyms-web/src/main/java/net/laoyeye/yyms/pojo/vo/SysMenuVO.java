package net.laoyeye.yyms.pojo.vo;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author laoyeye.net
 * @Description: 后台左侧菜单VO
 * @date 2019/5/3 23:20
 */
@Data
@Builder
public class SysMenuVO {
    private Long id;

    private Long pid;

    private String title;

    private String icon;

    private String url;
    
    private List<SysMenuVO> children;

}