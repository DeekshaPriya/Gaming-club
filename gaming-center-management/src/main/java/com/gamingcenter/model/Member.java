package com.gamingcenter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import com.gamingcenter.model.enums.MembershipType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "members")

public class Member {
	@Id
    private String id;
    
    @Indexed(unique = true)
    private String memberId;
    
    private String name;
    
    @Indexed(unique = true)
    private String email;
    
    @Indexed(unique = true)
    private String phone;
    
    private String address;
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal joiningFee;
    private MembershipType membershipType = MembershipType.BASIC;
    private boolean isActive = true;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    // Helper methods
    public void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
    
    public void deductBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
    
    public boolean hasEnoughBalance(BigDecimal amount) {
    	return this.balance.compareTo(amount) >= 0;

    }
    
 // Add these methods to your Member.java class

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getJoiningFee() {
        return joiningFee;
    }

    public void setJoiningFee(BigDecimal joiningFee) {
        this.joiningFee = joiningFee;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt=updatedAt;
    }


}
