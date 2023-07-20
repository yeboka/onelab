package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Before("execution(* org.example.service.UserService.save(..)) && args(userDTO)")
    public void logBeforeSave(JoinPoint joinPoint, UserDTO userDTO) {
        logger.info("Saving user with ID: {}", userDTO.getId());
    }

    @AfterReturning("execution(* org.example.service.UserService.save(..)) && args(userDTO)")
    public void logAfterSave(JoinPoint joinPoint, UserDTO userDTO) {
        logger.info("User saved with ID: {}", userDTO.getId());
    }


}
