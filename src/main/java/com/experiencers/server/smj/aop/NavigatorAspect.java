package com.experiencers.server.smj.aop;

import com.experiencers.server.smj.domain.Member;
import com.experiencers.server.smj.repository.MemberRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class NavigatorAspect {
    @Autowired
    private MemberRepository memberRepository;

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

    /*@Before("execution(* com.experiencers.server.smj.api.*.get*(..))")
    public void addUserInfo(JoinPoint joinPoint){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Before 시작!!!!");
        System.out.println(user.getUsername());
        Member member = memberRepository.findByEmail(user.getUsername()).get();

        System.out.println(member.getEmail());
        System.out.println(member.getNickname());
        System.out.println(member.getImage());
        System.out.println("Before 끝 !!");
    }*/
}
