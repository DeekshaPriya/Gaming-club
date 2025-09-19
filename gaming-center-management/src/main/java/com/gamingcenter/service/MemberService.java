package com.gamingcenter.service;
import com.gamingcenter.dto.request.MemberRegistrationRequest;
import com.gamingcenter.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {
	MemberResponse registerMember(MemberRegistrationRequest request);
    MemberResponse getMemberById(String memberId);
    List<MemberResponse> getAllActiveMembers();
    List<MemberResponse> getAllMembers();
    MemberResponse updateMember(String memberId, MemberRegistrationRequest request);
    void deactivateMember(String memberId);

}
