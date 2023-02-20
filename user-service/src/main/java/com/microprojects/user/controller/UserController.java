package com.microprojects.user.controller;

import com.microprojects.user.VO.ResponseTemplateVO;
import com.microprojects.user.entity.User;
import com.microprojects.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private static final String USER = "USER";
    int count = 1;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("inside saveUser of userController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")

    @CircuitBreaker(name = USER, fallbackMethod = "fallbackMethod")
    @Retry(name = USER, fallbackMethod = "fallbackMethod")
    @RateLimiter(name = USER)

    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        System.out.println("retry method called: " + count++ + " times at" + new Date());
        return userService.getUserWithDepartment(userId);
    }
 public ResponseTemplateVO fallbackMethod(Exception e){
     return new ResponseTemplateVO();
 }
}
