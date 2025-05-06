package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.RoomType;
import com.example.hotelmanagement.mapper.RoomTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    public int addRoomType(RoomType roomType) {
        return roomTypeMapper.insert(roomType);
    }

    public int deleteRoomType(Integer id) {
        return roomTypeMapper.deleteById(id);
    }

    public int updateRoomType(RoomType roomType) {
        return roomTypeMapper.update(roomType);
    }

    public RoomType getRoomTypeById(Integer id) {
        return roomTypeMapper.selectById(id);
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeMapper.selectAll();
    }
}