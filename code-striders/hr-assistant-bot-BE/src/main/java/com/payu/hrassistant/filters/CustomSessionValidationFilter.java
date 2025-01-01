//package com.payu.hrassistant.filters;
//
////import com.payu.hrassistant.usermanagement.service.RedisSessionService;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpServletResponseWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//@Component
//@Order(1)
//public class CustomSessionValidationFilter extends OncePerRequestFilter {
//
////    @Autowired
////    private RedisSessionService redisSessionService;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        // Create a new wrapper to allow multiple reads of request body
//        CustomHttpServletRequestWrapper wrappedRequest = new CustomHttpServletRequestWrapper(request);
//        CustomHttpServletResponseWrapper wrappedResponse = new CustomHttpServletResponseWrapper(response);
//
//        // Skip validation for login and register endpoints
//        String requestURI = request.getRequestURI();
//        if (requestURI.contains("/users/login") ||
//                requestURI.contains("/users/register")) {
//            filterChain.doFilter(wrappedRequest, wrappedResponse);
//            return;
//        }
//
//        // Check for Authorization
////        String sessionId = wrappedRequest.getHeader("Authorization");
////        if (sessionId == null || !redisSessionService.validateSession(sessionId)) {
////            wrappedResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////            wrappedResponse.getWriter().write("Invalid or expired session");
////            return;
////        }
//
//        filterChain.doFilter(wrappedRequest, wrappedResponse);
//    }
//}
//
//
//
