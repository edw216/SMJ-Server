package com.experiencers.server.smj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class NavigatorAspect {
    @AfterReturning(pointcut = "execution(* com.experiencers.server.smj.controller.*.*(..))", returning = "returnValue")
    public ModelAndView addNavigationFlag(JoinPoint joinPoint, ModelAndView returnValue) {
        String fullClassName = joinPoint.getTarget().getClass().getCanonicalName();
        System.out.println(fullClassName);
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1).toLowerCase();

        System.out.println(className);
        returnValue.getModel().put(className, true);
        System.out.println(returnValue.getModelMap().toString());
        return returnValue;
    }


}
