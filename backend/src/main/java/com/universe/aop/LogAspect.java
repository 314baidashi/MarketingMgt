package com.universe.aop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author yuanjs
 * @description:
 * @date 2020-04-08 11:30
 */
@Slf4j
@Aspect
@Component
public class LogAspect {



    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void pointCut() {
        //定义切点
    }

//    /**
//     * 发送日志
//     * @param logStr
//     */
//    private void sendLog(String cookies,String logStr) {
//        new Thread(() -> {
//            try {
//                Map<String,String> ck=HttpUtil.cookieToMap(cookies);
//                HashMap<String, String> hash = new HashMap<>();
//                hash.put("userName", hash.get("username"));
//                hash.put("projectId", hash.get("projectid"));
//                hash.put("content", logStr);
//                HttpUtil.sendPostRequest(url, hash);
//            } catch (Exception ex) {
//                log.error("LogAspect中向远程发送日志失败");
//            }
//        }).start();
//    }


    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        try {

            RequestAttributes ra = RequestContextHolder.getRequestAttributes();

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            HttpServletRequest request = (HttpServletRequest) ra.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String cookiesStr = request.getHeader("Cookie");
            if (!StringUtils.isEmpty(cookiesStr)) {
                log.info(cookiesStr);
            }
            //处理cookies

            Api api = method.getDeclaringClass().getAnnotation(Api.class);
            String apiValue = "", operationValue = "";
            if (api != null) {
                apiValue = api.value();
            }
            ApiOperation operation = method.getAnnotation(ApiOperation.class);
            if (operation != null) {
                operationValue = operation.value();
            }

            String logContent = String.format("%s-%s", apiValue, operationValue);
            log.info(logContent);

        } catch (Exception ex) {
            log.error("LogAspect采集日志发生错误");
            log.error(ex.getMessage(), ex);
        }
    }
}
