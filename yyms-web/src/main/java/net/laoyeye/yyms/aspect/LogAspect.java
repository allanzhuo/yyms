package net.laoyeye.yyms.aspect;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import net.laoyeye.utils.HttpClientUtil;
import net.laoyeye.utils.IpUtils;
import net.laoyeye.yyms.annotation.Log;
import net.laoyeye.yyms.pojo.domain.SysLogDO;
import net.laoyeye.yyms.pojo.domain.SysUserDO;
import net.laoyeye.yyms.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

/**
 * @author laoyeye
 * @Description: 审计日志
 * @date 2019/7/11 22:15
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    LogService logService;

    @Pointcut("@annotation(net.laoyeye.yyms.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogDO sysLogDO = new SysLogDO();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 注解上的描述
            sysLogDO.setOperation(log.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLogDO.setRequestMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append("  ").append(paramNames[i]).append(": ").append(args[i]);
            }
            sysLogDO.setParams(params.toString());
        }

        // 获取request
        HttpServletRequest request = HttpClientUtil.getHttpServletRequest();
        // 设置IP地址
        sysLogDO.setIp(IpUtils.getIpAddress(request));
        // 用户名
        SysUserDO currUser = (SysUserDO) SecurityUtils.getSubject().getPrincipal();
        // 获取当前登录用户名
        if (SecurityUtils.getSubject().isAuthenticated()) {
            SysUserDO user = (SysUserDO) SecurityUtils.getSubject().getPrincipal();
            sysLogDO.setUserName(user.getUserName());
        }
        sysLogDO.setResponseTime((int) time);
        // 保存系统日志
        logService.save(sysLogDO);
    }
}