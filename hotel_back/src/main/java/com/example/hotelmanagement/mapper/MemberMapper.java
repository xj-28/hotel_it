package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Insert("INSERT INTO member (member_name, member_phone, member_level, points) VALUES (#{member_name}, #{member_phone}, #{member_level}, #{points})")
    @Options(useGeneratedKeys = true, keyProperty = "member_id")
    int insert(Member member);

    @Delete("DELETE FROM member WHERE member_id = #{member_id}")
    int deleteById(Integer member_id);

    @Update("UPDATE member SET member_name = #{member_name}, member_phone = #{member_phone}, member_level = #{member_level}, points = #{points} WHERE member_id = #{member_id}")
    int update(Member member);

    @Select("SELECT member_id, member_name, member_phone, member_level, points, registration_date FROM member WHERE member_id = #{member_id}")
    Member selectById(Integer member_id);

    @Select("SELECT member_id, member_name, member_phone, member_level, points, registration_date FROM member")
    List<Member> selectAll();
}