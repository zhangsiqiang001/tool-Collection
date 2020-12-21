package com.itgod.restController.Myannotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @ClassName TestAspect
 * @Description TODO
 * @Date 2019/2/25/02515:12
 * @Version 1.0
 **/
@Aspect
@Component
public class FirstcutAspect {
   // @Pointcut("execution(public * com.test.my.annotation.TestController.*(..)) && @annotation(com.test.my.annotation.MyAnnotation)" )
    @Pointcut("@annotation(com.itgod.restController.Myannotation.firstcut)" )
    public void addAdvice(){}
    @Around("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint joinPoint){
        System.out.println("====Interceptor====");
        System.out.println("通知之开始");
        Object retmsg=null;
        try {
            retmsg = joinPoint.proceed();
            System.err.println("++++++++"+retmsg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("通知之结束 +retmsg+" + retmsg);

        Object result = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            String deviceId = (String) args[0];
            if (!"03".equals(deviceId)) {
                return "no anthorization";
            }
        }
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
    @Before("addAdvice()")
    public void before(JoinPoint joinPoint){
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        firstcut annotation = method.getAnnotation(firstcut.class);
        System.out.println("打印：" + annotation.Value() + " 开始前");
        //System.out.println("===开始前===");
    }

    @After("addAdvice()")
    public void after() {
        System.out.println("after方法执行后");
    }
}
