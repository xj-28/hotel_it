package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.RoomType;
import com.example.hotelmanagement.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-types")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    /**
     * 新增房间类型
     * @param roomType 房间类型对象
     * @return 受影响的行数
     */
    @PostMapping
    public int addRoomType(@RequestBody RoomType roomType) {
        return roomTypeService.addRoomType(roomType);
    }

    /**
     * 根据 ID 删除房间类型
     * @param id 房间类型 ID
     * @return 受影响的行数
     */
    @DeleteMapping("/{id}")
    public int deleteRoomType(@PathVariable Integer id) {
        return roomTypeService.deleteRoomType(id);
    }

    /**
     * 更新房间类型信息
     * @param roomType 房间类型对象
     * @return 受影响的行数
     */
    @PutMapping
    public int updateRoomType(@RequestBody RoomType roomType) {
        return roomTypeService.updateRoomType(roomType);
    }

    /**
     * 根据 ID 查询房间类型
     * @param id 房间类型 ID
     * @return 房间类型对象
     */
    @GetMapping("/{id}")
    public RoomType getRoomTypeById(@PathVariable Integer id) {
        return roomTypeService.getRoomTypeById(id);
    }

    /**
     * 查询所有房间类型
     * @return 房间类型列表
     */
    @GetMapping
    public List<RoomType> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }
}