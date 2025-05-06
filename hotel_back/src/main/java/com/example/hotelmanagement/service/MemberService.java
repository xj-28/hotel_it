package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.Member;
import com.example.hotelmanagement.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public int addMember(Member member) {
        return memberMapper.insert(member);
    }

    public int deleteMember(Integer member_id) {
        return memberMapper.deleteById(member_id);
    }

    public int updateMember(Member member) {
        return memberMapper.update(member);
    }

    public Member getMemberById(Integer member_id) {
        return memberMapper.selectById(member_id);
    }

    public List<Member> getAllMembers() {
        return memberMapper.selectAll();
    }
}