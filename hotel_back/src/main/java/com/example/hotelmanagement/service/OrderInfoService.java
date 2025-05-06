package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.OrderInfo;
import com.example.hotelmanagement.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public int addOrder(OrderInfo orderInfo) {
        return orderInfoMapper.insert(orderInfo);
    }

    public int deleteOrder(Integer order_id) {
        return orderInfoMapper.deleteById(order_id);
    }

    public int updateOrder(OrderInfo orderInfo) {
        return orderInfoMapper.update(orderInfo);
    }

    public OrderInfo getOrderById(Integer order_id) {
        return orderInfoMapper.selectById(order_id);
    }

    public List<OrderInfo> getAllOrders() {
        return orderInfoMapper.selectAll();
    }
}