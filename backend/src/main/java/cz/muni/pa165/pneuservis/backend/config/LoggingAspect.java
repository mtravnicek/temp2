package cz.muni.pa165.pneuservis.backend.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Peter on 10/19/2016.
 */
@Aspect
public class LoggingAspect {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

//  @Pointcut("within(com.myapp.service..*)")
  @Pointcut("execution(* sayHello(..))")
  public void loggingPointcut() {
  }


  @Around("loggingPointcut()")
  public Object log(ProceedingJoinPoint point) {

    Signature signature = point.getSignature();
    logger.debug("Entering {}.{} () with args = {}", signature.getDeclaringTypeName(), signature.getName(), Arrays.toString(point.getArgs()));

    try {
      Object result = point.proceed();
      logger.debug("Exiting {}.{} () with result = {}", signature.getDeclaringTypeName(), signature.getName(), result);
      return result;
    } catch (Throwable e) {
      logger.error("An error occurred in {}.{} with args = {}", signature.getDeclaringTypeName(), signature.getName(), Arrays.toString(point.getArgs()));
      return e;
    }
  }
}
