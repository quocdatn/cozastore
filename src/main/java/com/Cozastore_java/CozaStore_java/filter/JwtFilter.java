package com.Cozastore_java.CozaStore_java.filter;

import com.Cozastore_java.CozaStore_java.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    /**
     * Nhận được token truyền trên header
     * Giải mã token
     * Nếu giải mã thành công thì hợp lệ
     * Tạo chứng thực và cho đi vào link người dùng gọi
     */

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Lấy giá trị của header có key là Authorization
            String header = request.getHeader("Authorization");
            if (header.startsWith("Bearer ")) {
                // Cắt bỏ chữ Bearer và lấy ra token
                String token = header.substring(7);
                Claims claims = jwtHelper.decodeToken(token);
                if (claims != null) {
                    // Tạo chứng thực cho Security
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
                    context.setAuthentication(user);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }



        filterChain.doFilter(request, response);
    }

}
