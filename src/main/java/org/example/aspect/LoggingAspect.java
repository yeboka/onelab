package org.example.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    HttpServletRequest request;
    HttpServletResponse response;

    @Autowired
    public LoggingAspect(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String ip = request.getLocalAddr();
        String url = request.getRequestURL().toString();
        String username = request.getRemoteUser();

        request.setAttribute("requestTime", System.currentTimeMillis());

        logger.info("Request IP: {}", ip);
        logger.info("Request URL: {}", url);
        logger.info("Request Username: {}", username);
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        int statusCode = response.getStatus();
        long responseTime = System.currentTimeMillis();
        responseTime -= (long) request.getAttribute("requestTime");

        if (statusCode == 200)
            logger.info("Response: {}, duration: {}ms", result, responseTime);
        else
            logger.error("Response: {}, duration: {}ms", result, responseTime);
    }
}
