package com.gamingcenter.controller;

import com.gamingcenter.dto.request.MemberRegistrationRequest;
import com.gamingcenter.dto.response.MemberResponse;
import com.gamingcenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@CrossOrigin(origins="*")

public class MemberController {

	@Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberResponse> registerMember(@Valid @RequestBody MemberRegistrationRequest request) {
        System.out.println("POST /api/v1/members/register - Registering member: " + request.getEmail());
        try {
            MemberResponse response = memberService.registerMember(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error registering member: " + e.getMessage());
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable String memberId) {
        System.out.println("GET /api/v1/members/" + memberId + " - Fetching member");
        try {
            MemberResponse response = memberService.getMemberById(memberId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error fetching member: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(@RequestParam(defaultValue = "true") boolean activeOnly) {
        System.out.println("GET /api/v1/members - Fetching members (activeOnly: " + activeOnly + ")");
        try {
            List<MemberResponse> members = activeOnly ? 
                    memberService.getAllActiveMembers() : 
                    memberService.getAllMembers();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            System.out.println("Error fetching members: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable String memberId, 
                                                      @Valid @RequestBody MemberRegistrationRequest request) {
        System.out.println("PUT /api/v1/members/" + memberId + " - Updating member");
        try {
            MemberResponse response = memberService.updateMember(memberId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error updating member: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deactivateMember(@PathVariable String memberId) {
        System.out.println("DELETE /api/v1/members/" + memberId + " - Deactivating member");
        try {
            memberService.deactivateMember(memberId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("Error deactivating member: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Gaming Center API is working!");
}
}
