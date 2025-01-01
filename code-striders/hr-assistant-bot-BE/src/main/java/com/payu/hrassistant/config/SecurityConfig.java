//package com.payu.hrassistant.config;
//
//import com.payu.hrassistant.filters.CustomSessionValidationFilter;
//import jakarta.servlet.Filter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.context.SecurityContextPersistenceFilter;
//
//@Configuration
//public class SecurityConfig {
//
//    private final CustomSessionValidationFilter sessionValidationFilter;
//
//    public SecurityConfig(CustomSessionValidationFilter sessionValidationFilter) {
//        this.sessionValidationFilter = sessionValidationFilter;
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // Match EXACT paths in your controller
//                        .requestMatchers("/users/login", "/users/register").permitAll()
//                        .requestMatchers("/admin/**").hasRole("HR")
//                        .requestMatchers("/employee/**").hasRole("EMPLOYEE")
//                        .anyRequest().authenticated()
//                ).addFilterBefore((Filter) sessionValidationFilter, SecurityContextPersistenceFilter.class);// Add custom filter
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
