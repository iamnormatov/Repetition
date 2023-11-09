//package com.example.repetition.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/user/**").permitAll()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .and().build();
//    }
//}
