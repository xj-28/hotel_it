package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.RoomType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomTypeMapper {

    /**
     * 新增房间类型
     * @param roomType 房间类型对象
     * @return 受影响的行数
     */
    @Insert("INSERT INTO room_type (type_name, price, description) VALUES (#{typeName}, #{price}, #{description})")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description")
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(RoomType roomType);

    /**
     * 根据 ID 删除房间类型
     * @param id 房间类型 ID
     * @return 受影响的行数
     */
    @Delete("DELETE FROM room_type WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description")
    })
    int deleteById(Integer id);

    /**
     * 更新房间类型信息
     * @param roomType 房间类型对象
     * @return 受影响的行数
     */
    @Update("UPDATE room_type SET type_name = #{typeName}, price = #{price}, description = #{description} WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description")
    })
    int update(RoomType roomType);

    /**
     * 根据 ID 查询房间类型
     * @param id 房间类型 ID
     * @return 房间类型对象
     */
    @Select("SELECT * FROM room_type WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description")
    })
    RoomType selectById(Integer id);

    /**
     * 查询所有房间类型
     * @return 房间类型列表
     */
    @Select("SELECT * FROM room_type")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description")
    })
    List<RoomType> selectAll();
}