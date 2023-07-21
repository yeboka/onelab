package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggerAspect {
//    private final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
//
//    @Before("execution(* org.example.service.UserService.save(..)) && args(userDTO)")
//    public void logBeforeSave(JoinPoint joinPoint, UserDTO userDTO) {
//        logger.info("Saving user with ID: {}", userDTO.getId());
//    }
//
//    @AfterReturning("execution(* org.example.service.UserService.save(..)) && args(userDTO)")
//    public void logAfterSave(JoinPoint joinPoint, UserDTO userDTO) {
//        logger.info("User saved with ID: {}", userDTO.getId());
//    }
//
//    @Pointcut("execution(* org.example.service.UserService.findAllUser(..)))")
//    public void callUserServiceFindAllMethod() {}
//
//    @Pointcut("execution(* org.example.service.UserService.findUserById(..)))")
//    public void callUserServiceFindByIdMethod() {}
//
//    @AfterReturning(pointcut = "callUserServiceFindAllMethod()", returning = "users")
//    public void logAllUsers(JoinPoint joinPoint, List<UserDTO> users) {
//        String returnedList = users.stream().map(UserDTO::toString).collect(Collectors.joining(", \n"));
//        logger.info("Users:\n {}", returnedList);
//    }
//
//    @AfterReturning(pointcut = "callUserServiceFindByIdMethod()", returning = "user")
//    public void logFoundedUser(JoinPoint joinPoint, UserDTO user) {
//        logger.info("User with id {} was founded: {}", user.getId(), user);
//    }
//
//    @AfterReturning("execution(* org.example.service.UserService.removeUserById(..)) && args(id)")
//    public void logRemovedUser(JoinPoint joinPoint, Long id) {
//        logger.info("User with id {} was removed", id);
//    }
}
