package com.payu.hrassistant.usermanagement.service;

import com.payu.hrassistant.common.ErrorCodeConstants;
import com.payu.hrassistant.usermanagement.dtos.LoginRequest;
import com.payu.hrassistant.usermanagement.dtos.LoginResponse;
import com.payu.hrassistant.usermanagement.dtos.UserRegistrationRequest;
import com.payu.hrassistant.usermanagement.dtos.UserRegistrationResponse;
import com.payu.hrassistant.usermanagement.model.SessionStorage;
import com.payu.hrassistant.usermanagement.model.User;
import com.payu.hrassistant.usermanagement.repository.SessionStorageRepository;
import com.payu.hrassistant.usermanagement.repository.UserRepository;
import com.payu.hrassistant.common.CommonUtil;
import org.apache.kafka.common.security.auth.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.payu.hrassistant.common.CommonUtil.buildErrorResponse;

@Service
public class UserService {
 
    @Autowired
    private UserRepository userRepository;
 
//    @Autowired
//    private PasswordEncoder passwordEncoder;
 
    @Autowired
    private SessionStorageRepository sessionStorageRepository;
 
    // Register new user
    public Object registerUser(UserRegistrationRequest request) {
       try {
           User user = new User();
           user.setUsername(request.getUsername());
           user.setPassword(request.getPassword());
           user.setRole(request.getRole());
           user.setEmail(request.getEmail());
           user.setFirstName(request.getFirstName());
           user.setLastName(request.getLastName());
           userRepository.save(user);

           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return buildErrorResponse(ErrorCodeConstants.INTERNAL_SERVER_ERROR_CODE, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }
 
    // Login user and create session
    public Object login(LoginRequest request) {
        try {
            Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
            if (userOpt.isPresent() && Objects.equals(request.getPassword(), userOpt.get().getPassword())) {
                String sessionId = generateSessionId();
                sessionStorageRepository.save(new SessionStorage(sessionId, userOpt.get().getId(), LocalDateTime.now().plusHours(1)));

                LoginResponse loginResponse = LoginResponse.builder().userId(userOpt.get().getId()).role(userOpt.get().getRole().name()).build();
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(loginResponse);
            }else if(userOpt.isEmpty()){
                return buildErrorResponse(ErrorCodeConstants.USER_NOT_FOUND,"USER NOT FOUND",HttpStatus.BAD_REQUEST);
            }else{
                return buildErrorResponse(ErrorCodeConstants.BAD_CREDENTIALS,"BAD CREDENTIALS",HttpStatus.FORBIDDEN);
            }
        }catch (Exception e){
            return buildErrorResponse(ErrorCodeConstants.INTERNAL_SERVER_ERROR_CODE,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
 
    // Get user from session
    public User getUserFromSession(String sessionId) {
        Optional<SessionStorage> session = sessionStorageRepository.findBySessionId(sessionId);
        if (session.isPresent() && session.get().getExpiresAt().isAfter(LocalDateTime.now())) {
            return userRepository.findById(session.get().getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        }
        throw new RuntimeException("Session expired or invalid");
    }



    // Utility to generate session ID
    private String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}