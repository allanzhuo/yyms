package net.laoyeye.yyms.pojo.vo;

import java.util.List;

import lombok.*;

/**
 * @author laoyeye.net
 * @Description: 后台左侧菜单VO
 * @date 2019/5/3 23:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuVO {
    private Long id;

    private Long pid;

    private String title;

    private Integer type;

    private String icon;

    private String url;
    
    private List<SysMenuVO> children;
    @Builder.Default
    private Boolean spread = false;
    @Builder.Default
    private Boolean checked = false;

}