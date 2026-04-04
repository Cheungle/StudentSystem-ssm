package org.util.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.common.Result;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LogAdvice {

    @Pointcut("execution(* org.service.ScService.*(..))")
    public void coursePointcut() {}
    @Pointcut("execution(* org.service.ScService.selectOneCourse(..))")
    public void selectOneCourse() {}

    @Pointcut("execution(* org.service.ScService.getSelectResult(..))")
    public void getSelectResult() {}

    @Pointcut("execution(* org.service.ScService.cancelOneCourse(..))")
    public void cancelOneCourse() {}

    @Before("selectOneCourse()")
    public void startSelect(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("学生{}开始选课{}操作",args[1],args[0]);
    }
    @AfterReturning(value = "getSelectResult()",returning = "result")
    public void endSelect(Result result) {
        String msg = result.getMsg();
        log.info("选课结果：{}",msg);
    }
    @Before("cancelOneCourse()")
    public void startCancel(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("学生{}开始退课{}操作",args[1],args[0]);
    }
    @AfterReturning(value = "cancelOneCourse()",returning = "result")
    public void endCancel(Result result) {
        String msg = result.getMsg();
        log.info("退课结果：{}",msg);
    }

    @Around("coursePointcut()")
    public Object calculateTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long time = System.currentTimeMillis() - start;
        String methodName = pjp.getSignature().getName();
        log.info("方法：{} 耗时: {}ms", methodName,time);
        return result;
    }
}
