package net.laoyeye.yyms.controller;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.enums.ResultEnum;
import net.laoyeye.pojo.Result;
import net.laoyeye.utils.AjaxUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
//    @Autowired
//    LogService logService;

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        if (AjaxUtils.jsAjax(request)) {
            return Result.build(ResultEnum.UN_AUTHORIZED.getCode(), ResultEnum.UN_AUTHORIZED.getValue());
        }
        return new ModelAndView("error/403");
    }


    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            if (AjaxUtils.jsAjax(request)) {
                return Result.build(ResultEnum.NOT_FOUND.getCode(), ResultEnum.NOT_FOUND.getValue());
            }
            return new ModelAndView("error/404");
        } else {
            if (AjaxUtils.jsAjax(request)) {
                return Result.build(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getValue());
            }
            return new ModelAndView("error/500");
        }
    }
}
