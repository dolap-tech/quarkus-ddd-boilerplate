package com.dolap.quarkus.ddd.infrastructure.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.UUID;

@Logged
@Priority(1)
@Interceptor
public class LoggingInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @AroundInvoke
    Object logInvocation(InvocationContext context) throws Exception {
        MDC.put("requestId", UUID.randomUUID().toString());

        logger.info("Class: " + context.getMethod().getDeclaringClass().getSimpleName() + " - Method: " + context.getMethod().getName() + " - Params: " +
                Arrays.toString(context.getParameters()));
        Object returnVal = context.proceed();
        if (returnVal != null) {
            logger.info("Return Response: " + returnVal.toString());
        }
        MDC.clear();
        return returnVal;
    }

}
