package com.testing_system.tester.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/*
Класс-конфигуратор безопасности
В данный момент разрешает доступ ко всем эндпоинтам для тестирования методов
Будет реализован после утверждения правильности работы всех методов
 */

@Configuration
public class SecurityBeanConfigurer {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{

        return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.anyRequest().permitAll()).build();
    }
}
