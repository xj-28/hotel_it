package com.example.hotelmanagement.interceptor;

import com.example.hotelmanagement.util.TokenManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie name: " + cookie.getName() + ", value: " + cookie.getValue());
                if ("admin_token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (TokenManager.validateToken(token)) {
                        return true;
                    }
                }
            }
        }
        System.out.println("No valid admin_token found.");
        response.sendRedirect("/admin/login?error=login_required");
        return false;
    }
}