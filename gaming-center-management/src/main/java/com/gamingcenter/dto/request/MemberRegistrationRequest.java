package com.gamingcenter.dto.request;

import com.gamingcenter.model.enums.MembershipType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MemberRegistrationRequest {
	@NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please provide a valid 10-digit phone number")
    private String phone;
    
    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;
    
    private MembershipType membershipType = MembershipType.BASIC;
 // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public MembershipType getMembershipType() {
        return membershipType;
  }
    
    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

}
