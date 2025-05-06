package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.Admin;
import com.example.hotelmanagement.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin validateAdmin(String admin_username, String admin_password) {
        return adminMapper.findAdminByUsernameAndPassword(admin_username, admin_password);
    }
}