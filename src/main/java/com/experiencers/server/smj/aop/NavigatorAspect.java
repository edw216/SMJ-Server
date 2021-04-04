package com.experiencers.server.smj.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
//AOP => 공통된 기능을 재사용하는 기법
@Aspect
@Component
public class NavigatorAspect { //afterreturning -> 정상적인 기능 반환 이후 아래의 public 을 수행
    @AfterReturning(pointcut = "execution(* com.experiencers.server.smj.controller.*.*(..))", returning = "returnValue")
    public ModelAndView addNavigationFlag(JoinPoint joinPoint, ModelAndView returnValue) {
        String fullClassName = joinPoint.getTarget().getClass().getCanonicalName();
        System.out.println(fullClassName);//조인포인트(메소드 단위) -> 클래스 정보 -> 클래스 이름 -> 네비게이션 사용
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1).toLowerCase();
        //인덱스의 마지막 .부분에서 +1 하여 boardcontroller 가 추가적으로 실행되도록 한다.
//target -> 부가 기능을 부여할 대상 (빈 타입이 아닌 것)
        System.out.println(className);
        returnValue.getModel().put(className, true); //추가되는 부분
        System.out.println(returnValue.getModelMap().toString());
        return returnValue;
    }
//

}
