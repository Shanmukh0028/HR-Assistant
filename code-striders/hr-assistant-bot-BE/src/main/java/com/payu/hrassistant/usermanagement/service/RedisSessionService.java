//package com.payu.hrassistant.usermanagement.service;
//
//import com.payu.hrassistant.usermanagement.model.SessionStorage;
//import com.payu.hrassistant.usermanagement.model.User;
//import com.payu.hrassistant.usermanagement.repository.SessionStorageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class RedisSessionService {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    private static final long SESSION_TIMEOUT_SECONDS = 7200; // 2 hours
//
//    public String createSession(User user,String sessionId) {
//
//        // Store user with session
//        redisTemplate.opsForValue().set(
//                "session:" + sessionId,
//                user,
//                Duration.ofSeconds(SESSION_TIMEOUT_SECONDS)
//        );
//
//        return sessionId;
//    }
//
//    public User getSession(String sessionId) {
//        return (User) redisTemplate.opsForValue().get("session:" + sessionId);
//    }
//
//    public void invalidateSession(String sessionId) {
//        redisTemplate.delete("session:" + sessionId);
//    }
//
//    public boolean validateSession(String sessionId) {
//        return Boolean.TRUE.equals(redisTemplate.hasKey("session:" + sessionId));
//    }
//
//    public void extendSession(String sessionId) {
//        User user = getSession(sessionId);
//        if (user != null) {
//            redisTemplate.expire(
//                    "session:" + sessionId,
//                    SESSION_TIMEOUT_SECONDS,
//                    TimeUnit.SECONDS
//            );
//        }
//    }
//}