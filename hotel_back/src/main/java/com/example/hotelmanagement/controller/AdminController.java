package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.Admin;
import com.example.hotelmanagement.service.AdminService;
import com.example.hotelmanagement.util.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController

public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String admin_username, @RequestParam String admin_password, HttpServletResponse response) {
        Admin admin = adminService.validateAdmin(admin_username, admin_password);
        if (admin != null) {
            String token = TokenManager.generateToken(admin.getAdmin_id());
            Cookie cookie = new Cookie("admin_token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "登录成功，欢迎 " + admin.getAdmin_username() + "（" + admin.getAdmin_role() + "）";
        }
        return "用户名或密码错误";
    }

    @PostMapping("/admin/logout")
    public String adminLogout(@CookieValue(value = "admin_token", required = false) String token) {
        if (token != null) {
            TokenManager.removeToken(token);
        }
        return "已退出登录";
    }

    @GetMapping("/admin/protected")
    public String protectedPage(@CookieValue(value = "admin_token", required = false) String token) {
        try {
            if (token != null && TokenManager.validateToken(token)) {
                return "这是受保护的页面";
            }
            return "请先登录";
        } catch (Exception e) {
            // 记录异常信息
            e.printStackTrace();
            return "发生错误，请稍后再试";
        }
    }
}
