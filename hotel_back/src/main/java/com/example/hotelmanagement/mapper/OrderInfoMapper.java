package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    @Insert("INSERT INTO order_info (member_id, room_type_id, check_in_date, check_out_date, order_status, order_amount) " +
            "VALUES (#{member_id}, #{room_type_id}, #{check_in_date}, #{check_out_date}, #{order_status}, #{order_amount})")
    @Options(useGeneratedKeys = true, keyProperty = "order_id")
    int insert(OrderInfo orderInfo);

    @Delete("DELETE FROM order_info WHERE order_id = #{order_id}")
    int deleteById(Integer order_id);

    @Update("UPDATE order_info SET member_id = #{member_id}, room_type_id = #{room_type_id}, " +
            "check_in_date = #{check_in_date}, check_out_date = #{check_out_date}, " +
            "order_status = #{order_status}, order_amount = #{order_amount} WHERE order_id = #{order_id}")
    int update(OrderInfo orderInfo);

    @Select("SELECT order_id, member_id, room_type_id, check_in_date, check_out_date, order_status, order_amount " +
            "FROM order_info WHERE order_id = #{order_id}")
    OrderInfo selectById(Integer order_id);

    @Select("SELECT order_id, member_id, room_type_id, check_in_date, check_out_date, order_status, order_amount " +
            "FROM order_info")
    List<OrderInfo> selectAll();
}