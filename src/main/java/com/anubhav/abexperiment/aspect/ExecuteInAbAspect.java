package com.anubhav.abexperiment.aspect;

import com.anubhav.abexperiment.annotation.ExecuteInAB;
import com.anubhav.abexperiment.service.VariantService;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ExecuteInAbAspect {

  private final VariantService variantService;

  @Around(value = "@annotation(executeInAB)")
  public Object executeinAB(ProceedingJoinPoint proceedingJoinPoint, ExecuteInAB executeInAB) throws Throwable {
    var targetObject = proceedingJoinPoint.getTarget();
    String expId = executeInAB.value();
    String methodToBeExecuted = variantService.fetchVariant(expId);
    log.info("Variant fetched :: {}", methodToBeExecuted);
    var args = proceedingJoinPoint.getArgs();
    var dataTypes = getArgsTypes((MethodInvocationProceedingJoinPoint) proceedingJoinPoint);
    Method methodToExecute = targetObject.getClass().getMethod(methodToBeExecuted, dataTypes);
    return methodToExecute.invoke(targetObject, args);
  }

  private Class<?>[] getArgsTypes(MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint) {
    MethodSignature methodSignature = (MethodSignature) methodInvocationProceedingJoinPoint.getSignature();
    return methodSignature.getParameterTypes();
  }
}
