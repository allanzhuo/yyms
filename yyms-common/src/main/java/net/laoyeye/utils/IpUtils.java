package net.laoyeye.utils;

import net.laoyeye.pojo.IpInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author laoyeye
 * @Description: IP工具类
 * @date 2019/7/11 23:26
 */
public class IpUtils {

    public static String getIpAddress(HttpServletRequest request)
    {
        String ip;
        try
        {
            ip = request.getHeader("x-forwarded-for");
            if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if ((StringUtils.isEmpty(ip)) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if ((StringUtils.isEmpty(ip)) || ("unknown".equalsIgnoreCase(ip))) {
                ip = request.getRemoteAddr();
            }
        }
        catch (Exception e)
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static IpInfo getIpInfo(String ip) {
        String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
        String resp = HttpClientUtil.doGet(url);
        return JSONUtils.jsonToBean(resp, IpInfo.class);
    }

    public static String getIpCnInfo(IpInfo ipInfo) {
        String temp = ipInfo.getData().getCountry() + ipInfo.getData().getRegion() + ipInfo.getData().getCity();
        if (!ipInfo.getData().getCounty().toLowerCase().contains("x")) {
            return temp + ipInfo.getData().getCounty();
        } else {
            return temp;
        }
    }
}
