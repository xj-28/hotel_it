package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.OrderInfo;
import com.example.hotelmanagement.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping
    public int addOrder(@RequestBody OrderInfo orderInfo) {
        return orderInfoService.addOrder(orderInfo);
    }

    @DeleteMapping("/{order_id}")
    public int deleteOrder(@PathVariable Integer order_id) {
        return orderInfoService.deleteOrder(order_id);
    }

    @PutMapping
    public int updateOrder(@RequestBody OrderInfo orderInfo) {
        return orderInfoService.updateOrder(orderInfo);
    }

    @GetMapping("/{order_id}")
    public OrderInfo getOrderById(@PathVariable Integer order_id) {
        return orderInfoService.getOrderById(order_id);
    }

    @GetMapping
    public List<OrderInfo> getAllOrders() {
        return orderInfoService.getAllOrders();
    }
}