package net.laoyeye.yyms.config;

import net.laoyeye.utils.AjaxUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author laoyeye.net
 * @Description: 自定义Filter，解决ajax超时问题
 * @date 2019/6/9 23:35
 */
public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {

    public ShiroFormAuthenticationFilter() {
        super();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return super.onAccessDenied(request, response);
        } else {
            if (AjaxUtils.jsAjax((HttpServletRequest) request)) {
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                httpServletResponse.addHeader("REQUIRE_AUTH", "true");
//                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }

}