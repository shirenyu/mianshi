package com.config;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LubanAspect {

    @Pointcut("execution(* com.dao.*.*(..))")
    public void pointCut(){

    }

    @Before(value = "pointCut()")
    public void beforeAdvice(){
        System.out.println("不要杀他");
    }

}
