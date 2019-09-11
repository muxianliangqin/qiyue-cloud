package com.qiyue.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class TimeAspect {
//    @AfterReturning("execution(* com.mycompany.financial.nirvana..*Mapper.*(..))")
//    public void logServiceAccess(JoinPoint joinPoint) {
//        logger.info("Completed: " + joinPoint);
//    }

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 定义在web包或者子包
     * ~ 第三个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.qiyue.*.controller..*.*(..))")
    private void controllerCutMethod() {
    }

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerCutMethod()")
    public Object serviceAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("\n调用controller方法：{},参数:{},耗时:{}毫秒", pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()), (end - begin));
        return obj;
    }
}
