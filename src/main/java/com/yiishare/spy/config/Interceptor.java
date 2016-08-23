package com.yiishare.spy.config;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.yiishare.spy.utils.DateUtil;

/**
 * 
 * <Description> <br> 
 *  
 * @author zhang.zhigao<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2016年8月16日 <br>
 * @since V1.0<br>
 * @see com.yiishare.spy.config <br>
 */
@Aspect
@Configuration
public class Interceptor {

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(Interceptor.class);
    
    // 切入点表达式按需配置
    @Pointcut("execution(* com.yiishare.*.*(..))")
    private void myPointcut() {
    }
    
    @Before("execution(* com.yiishare.*.*(..))")
    private void output() {
        logger.debug(DateUtil.getCurrentTimeString());
        System.out.println(DateUtil.getCurrentTimeString());
    }
}
