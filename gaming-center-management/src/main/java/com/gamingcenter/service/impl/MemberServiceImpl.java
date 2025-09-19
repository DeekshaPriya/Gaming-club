package com.gamingcenter.service.impl;

import com.gamingcenter.dto.request.MemberRegistrationRequest;
import com.gamingcenter.dto.response.MemberResponse;
import com.gamingcenter.model.Member;
import com.gamingcenter.repository.MemberRepository;
import com.gamingcenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberResponse registerMember(MemberRegistrationRequest request) {
        System.out.println("Registering new member: " + request.getEmail());
        
        // Check if member already exists
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Member with email already exists: " + request.getEmail());
        }
        
        if (memberRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new RuntimeException("Member with phone already exists: " + request.getPhone());
        }
        
        // Create member
        String memberId = generateMemberId();
        BigDecimal joiningFee = BigDecimal.valueOf(request.getMembershipType().getJoiningFee());
        
        Member member = new Member();
        member.setMemberId(memberId);
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setAddress(request.getAddress());
        member.setMembershipType(request.getMembershipType());
        member.setJoiningFee(joiningFee);
        member.setBalance(joiningFee);
        member.setActive(true);
        member.setCreatedAt(LocalDateTime.now());
        member.setUpdatedAt(LocalDateTime.now());
        

        
        Member savedMember = memberRepository.save(member);
        System.out.println("Member registered successfully with ID: " + memberId);
        
        return mapToResponse(savedMember);
    }

    @Override
    public MemberResponse getMemberById(String memberId) {
        System.out.println("Fetching member with ID: " + memberId);
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found: " + memberId));
        return mapToResponse(member);
    }

    @Override
    public List<MemberResponse> getAllActiveMembers() {
        System.out.println("Fetching all active members");
        return memberRepository.findByIsActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        System.out.println("Fetching all members");
        return memberRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponse updateMember(String memberId, MemberRegistrationRequest request) {
        System.out.println("Updating member: " + memberId);
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found: " + memberId));
        
        member.setName(request.getName());
        member.setAddress(request.getAddress());
        member.setUpdatedAt(LocalDateTime.now());
        
        Member updatedMember = memberRepository.save(member);
        return mapToResponse(updatedMember);
    }

    @Override
    public void deactivateMember(String memberId) {
        System.out.println("Deactivating member: " + memberId);
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found: " + memberId));
        
        member.setActive(false);
        member.setUpdatedAt(LocalDateTime.now());
        memberRepository.save(member);
    }

    private String generateMemberId() {
        return "GC" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    private MemberResponse mapToResponse(Member member) {
        MemberResponse response = new MemberResponse();
        response.setId(member.getId());
        response.setMemberId(member.getMemberId());
        response.setName(member.getName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setAddress(member.getAddress());
        response.setBalance(member.getBalance());
        response.setJoiningFee(member.getJoiningFee());
        response.setMembershipType(member.getMembershipType());
        response.setActive(member.isActive());
        response.setCreatedAt(member.getCreatedAt());
        response.setUpdatedAt(member.getUpdatedAt());
        return response;
}
}
