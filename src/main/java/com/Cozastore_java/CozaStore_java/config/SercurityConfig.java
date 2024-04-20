package com.Cozastore_java.CozaStore_java.config;

import com.Cozastore_java.CozaStore_java.filter.JwtFilter;
import com.Cozastore_java.CozaStore_java.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SercurityConfig {
    // Khai báo chuẩn mã hóa Bcrypt và lưu trữ trên IOC
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private CustomAuthenProvider authenticationProvider;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider)
                .build();

    }

    /**
     * /admin/save: ADMIN hoặc SAVE
     * /admin/delete: DELETE
     *
     */

    // Đây là filter dùng để custom rule liên quan tới link hoặc cấu hình của security
    // Java 8,11: antMatchers
    // Java 17: requestAntMatchers
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() // Tắt cấu hình liên quan tới tấn công CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Khai báo không sử dụng session trong project
                .and()
                .authorizeHttpRequests() // Quy định lại các rule liên quan tới chứng thực cho link được gọi
                .requestMatchers("/signin","/signup","/demo/**","/category/**","/product/**","/size/**","/color/**").permitAll()
                .anyRequest().authenticated() // Tất cả các link còn lại đều phải chứng thực
//                .and()
//                .httpBasic()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
