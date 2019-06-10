package net.laoyeye.utils;

import javax.servlet.http.HttpServletRequest;
/**
 * 判断Ajax请求工具类
 * @author 小卖铺的老爷爷
 * @date 2017年6月1日
 * @website www.laoyeye.net
 */
public class AjaxUtils {
    public static boolean jsAjax(HttpServletRequest req){
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(req.getHeader("x-requested-with")) && req.getHeader("x-requested-with").equals("XMLHttpRequest")){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
