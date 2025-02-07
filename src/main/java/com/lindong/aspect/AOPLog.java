package com.lindong.aspect;

import com.lindong.domain.SysLog;
import com.lindong.domain.User;
import com.lindong.service.ISysLogService;
import com.lindong.utils.IPUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class AOPLog {

    private Date startTime;
    @Resource
    private ISysLogService sysLogService;
    @Resource
    private HttpServletRequest request;

    @Pointcut("execution(* com.lindong.controller.*ManageController.*(..))")
    public void pointCut(){
        System.out.println("22222222222");
    }

    @Around("pointCut()")
    public Object recordSysLog(ProceedingJoinPoint joinPoint) throws Throwable{
        startTime = new Date();
        System.out.println("========"+"日志记录开始...");
        //业务方法执行
        Object result = joinPoint.proceed();
        try{
            handle(joinPoint);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void handle(ProceedingJoinPoint joinPoint) throws Exception {
        //获取类名
        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = clazz.getMethod(signature.getName(),signature.getParameterTypes());
        RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class); //获取请求一级目录
        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);   //获取请求二级目录
        Log logAnnotation = method.getAnnotation(Log.class);        //获取自定义注解
        if (methodAnnotation == null){          //二级目录为空,直接返回
            return;
        }
        if (method.getName().contains("list")){
            return;
        }
        String operation = null;
        if (logAnnotation != null){
            operation = logAnnotation.operation();
        }
        String url;
        if (clazzAnnotation != null){
            url =clazzAnnotation.value()[0] + methodAnnotation.value()[0];
        }else {
            url = methodAnnotation.value()[0];
        }
        SysLog sysLog = new SysLog();
        sysLog.setCreate_time(startTime);
        sysLog.setOperation(operation);
        sysLog.setIp(IPUtils.getIpAddr(request));
        sysLog.setUsername((String) request.getSession().getAttribute("username"));
        sysLog.setMethod("[类名]:"+clazz.getName() + " [方法名]:"+method.getName());
        sysLog.setUrl(url);
        sysLogService.insertSysLog(sysLog);
        return;
    }

}

























