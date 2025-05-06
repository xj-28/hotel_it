package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("SELECT admin_id, admin_username, admin_password, admin_role " +
            "FROM admin WHERE admin_username = #{admin_username} AND admin_password = #{admin_password}")
    Admin findAdminByUsernameAndPassword(@Param("admin_username") String username, @Param("admin_password") String password);
}