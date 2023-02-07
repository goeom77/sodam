package com.samsung.sodam.config;

import com.samsung.sodam.jwt.JwtAuthenticationFilter;
import com.samsung.sodam.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.csrf().disable()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션 사용 x
               .and()
               .authorizeRequests()

               .antMatchers("/user/**", "/api/report","/api/trouble/comment").authenticated()
               .antMatchers( "/api/trouble/writing").hasRole("CLIENT")
               .antMatchers("/admin/**").hasRole("ADMIN")
               .anyRequest().permitAll()
               .and()
               .formLogin()
               .loginPage("/login")
               .loginProcessingUrl("/loginProc")    // 자동로그인 위함
               .defaultSuccessUrl("/")
               .and()
               // JwtAuthenticationFilter 는 UsernamePasswordAuthenticationFilter 전에 넣음
               .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
               .build();
   }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}