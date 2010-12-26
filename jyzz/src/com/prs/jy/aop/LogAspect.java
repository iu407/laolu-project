package com.prs.jy.aop;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


//@Aspect
//@Component
public class LogAspect {
	  /** 定义共用方法切入点 
     */ 
//    @Pointcut("execution(* *.*(..))")
     @Pointcut("execution(* com.prs.jy.controller.*.*(..))")   
    public void inPublicMethod() {
    	System.out.println("controller*********************");
	} 
    
    /** 定义数据访问类方法切入点 
     */ 
    @Pointcut("execution(* com.huawei.support.*.dao.*.*(..))") 
	public void inDAOPackage() {

	} 
    
    /** 定义业务处理类方法切入点 
     */ 
    @Pointcut("execution(* com.prs.service.*.*(..))") 
    public void inServicePackage() 
    { 
        
    } 
    
    /** 定义对外服务类方法切入点 
     */ 
    @Pointcut("execution(* com.huawei.support.*.ws.*.*(..))") 
    public void inWebServicePackage() 
    { 
        
    } 
    
    /** 定义总切入点 
     */ 
    @Pointcut("inPublicMethod() && (inDAOPackage() || inServicePackage() ||inWebServicePackage())") 
    public void supportAOP() 
    { 
        
    } 
    
    /** 切入点执行范围 
     * @param pjp       切入点 
     * @throws Throwable 切入点抛出的异常 
     */ 
    @Around("supportAOP()") 
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable 
    { 
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
    
    /** 切入点抛出异常 
     * @param jp        切入点 
     * @param ex        抛出的异常 
     */ 
    @AfterThrowing(pointcut = "supportAOP()", throwing = "ex") 
    public void doThrowing(JoinPoint jp, Throwable ex) 
    { 
        Logger log = Logger.getLogger(jp.getTarget().getClass()); 
        log.error(ex.getMessage(), ex); 
    } 

}
