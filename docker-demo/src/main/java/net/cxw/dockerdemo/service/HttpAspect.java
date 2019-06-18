package net.cxw.dockerdemo.service;

import net.cxw.dockerdemo.util.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;


/**
 * 日志切面操作
 */

@Aspect
@Component
public class HttpAspect {

    private  final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * net.cxw.dockerdemo.controller.*.*(..))")
    public void log(){
    }

    @Around("log()")
    public Object aroundLogCalls(ProceedingJoinPoint jp) throws Throwable {
        logger.info("方法环绕start.....");
        Object o = null;
        try {
            o = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info("方法环绕end.....");
        return o;
    }
    /**
     * 打印请求的url method ip 类方法  参数
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws IOException, ServletException {
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =attributes.getRequest();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        MyLog myLog = method.getAnnotation(MyLog.class);

        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.info("args={}",joinPoint.getArgs());
        //myLog
        String value = myLog.value();
        logger.info("myLog={}", value);

    }


    // @After("execution(public * com.tg.controller.GirlController.*(..))")
    @After("log()")
    public void doAfter(){
        logger.info("方法结束");
    }

    /**
     * 打印请求的具体内容
     * @param objects
     */
    @AfterReturning(returning = "objects",pointcut = "log()")
    public void  doAfterReturning(Objects objects){
        logger.info("response={}",objects);
    }


    @AfterThrowing("log()")
    public void cathInfo(){
        logger.info("异常信息");
    }

}
