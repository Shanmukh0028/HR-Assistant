package com.payu.hrassistant.usermanagement.controller;

import com.mysql.cj.log.Log;
import com.payu.hrassistant.usermanagement.dtos.LoginRequest;
import com.payu.hrassistant.usermanagement.dtos.LoginResponse;
import com.payu.hrassistant.usermanagement.dtos.UserRegistrationRequest;
import com.payu.hrassistant.usermanagement.model.User;
import com.payu.hrassistant.usermanagement.repository.UserRepository;
//import com.payu.hrassistant.usermanagement.service.RedisSessionService;
import com.payu.hrassistant.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private RedisSessionService redisSessionService;

    @Autowired
    private UserService userService;
    @PostMapping(value = "/register",produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {

        return (ResponseEntity<?>) userService.registerUser(userRegistrationRequest);
    }

    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return (ResponseEntity<LoginResponse>) userService.login(loginRequest);
    }

    @PostMapping(value = "/logout",produces = "application/json",
            consumes = "application/json")
    public String logout(@CookieValue("SESSIONID") String sessionId) {
//        redisSessionService.invalidateSession(sessionId);
        return "Logout successful!";
    }
 
    @GetMapping(value = "/me",produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String sessionId) {
        User user = userService.getUserFromSession(sessionId);
        return ResponseEntity.ok(user);
    }
}
 