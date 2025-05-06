package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.Member;
import com.example.hotelmanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public int addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @DeleteMapping("/{member_id}")
    public int deleteMember(@PathVariable Integer member_id) {
        return memberService.deleteMember(member_id);
    }

    @PutMapping
    public int updateMember(@RequestBody Member member) {
        return memberService.updateMember(member);
    }

    @GetMapping("/{member_id}")
    public Member getMemberById(@PathVariable Integer member_id) {
        return memberService.getMemberById(member_id);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
}