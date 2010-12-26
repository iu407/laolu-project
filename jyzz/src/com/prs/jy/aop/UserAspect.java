package com.prs.jy.aop;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;
@Repository("userAspect")
@Aspect
public class UserAspect {
	@Around("execution(* com.jy.*.*(..))") 
	public Object aroundController(ProceedingJoinPoint pjp) throws Throwable {
		Logger log = Logger.getLogger(pjp.getTarget().getClass()); 
        StringBuilder sb = new StringBuilder(); 
        sb.append("[") 
                .append(pjp.getTarget().getClass().getName()) 
                .append(".") 
                .append(pjp.getSignature().getName()) 
                .append("]"); 
        
        long begin = System.currentTimeMillis(); 
        sb.append("开始时间：[").append(DateUtils.format(begin, DateUtils.ISO8601_DATE_PATTERN)).append("]"); 
        //实际方法执行 
        Object result = pjp.proceed(); 
        
        long end = System.currentTimeMillis(); 
        sb.append("结束时间：[") 
                .append(DateUtils.format(begin, DateUtils.ISO8601_DATE_PATTERN)) 
                .append("]"); 
        sb.append("共耗费：[").append((end - begin)).append("ms]"); 
        log.info(sb.toString()); 
        return result; 
	}
	
    @Pointcut("execution(* com.prs.jy.controller.*.*(..))")   
    public void controllerPointCut() {
    	
    }   
       
//    @Before("controllerPointCut()")   
    public void before() {
//        System.out.println("before method...");
    }
    

}
