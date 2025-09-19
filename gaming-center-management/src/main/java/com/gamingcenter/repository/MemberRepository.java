package com.gamingcenter.repository;

import com.gamingcenter.model.Member;
import com.gamingcenter.model.enums.MembershipType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
	// Find by unique identifiers
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhone(String phone);
    
    // Find by status
    List<Member> findByIsActiveTrue();
    List<Member> findByIsActiveFalse();
    
    // Find by membership type
    List<Member> findByMembershipType(MembershipType membershipType);
    
    // Find by balance conditions
    List<Member> findByBalanceGreaterThan(BigDecimal amount);
    List<Member> findByBalanceLessThan(BigDecimal amount);
    
    // Find by date range
    List<Member> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Custom queries
    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<Member> findByNameContainingIgnoreCase(String name);
    
    @Query("{'isActive': true, 'balance': {$gte: ?0}}")
    List<Member> findActiveMemersWithMinBalance(BigDecimal minBalance);
    
    // Count queries
    long countByMembershipType(MembershipType membershipType);
    long countByIsActiveTrue();

}
