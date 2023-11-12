package com.moing.bo.backend.global.config.security;

import com.moing.bo.backend.domain.admin.domain.service.AdminGetService;
import com.moing.bo.backend.global.config.security.filter.JwtAccessDeniedHandler;
import com.moing.bo.backend.global.config.security.filter.JwtAuthenticationEntryPoint;
import com.moing.bo.backend.global.config.security.jwt.JwtSecurityConfig;
import com.moing.bo.backend.global.config.security.jwt.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final TokenUtil tokenUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final AdminGetService adminGetService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                // Exception Handling
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) //인증되지 않은 사용자가 보호된 리소스에 액세스 할 때 호출되는 JwtAuthenticationEntryPoint 설정
                        .accessDeniedHandler(jwtAccessDeniedHandler))     //권한이 없는 사용자가 보호된 리소스에 액세스 할 때 호출되는 JwtAccessDeniedHandler 설정
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Spring Security에서 세션을 사용하지 않도록 설정
                )
                // Request Authorization
                .authorizeRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/api/auth/**", "/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll() // 해당 경로들은 접근을 허용
                        .anyRequest().authenticated()
                )
                // Apply JWT Security Config
                .apply(new JwtSecurityConfig(tokenUtil, adminGetService));

        return http.build();
    }


    /**
     * 정적 파일은 항상 모두 허용
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resource/**", "/css/**", "/js/**", "/img/**", "/lib/**", "/test/**", "/swagger-ui/**");
    }
}