package net.laoyeye.yyms.pojo.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页基础查询
 * @author laoyeye.net
 * @date 2018年1月24日
 */
@Getter
@Setter
public class BaseQuery implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int page;

    private int limit;

}
