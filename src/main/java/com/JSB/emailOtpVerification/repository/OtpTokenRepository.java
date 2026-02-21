package com.JSB.emailOtpVerification.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.JSB.emailOtpVerification.models.OtpToken;

import jakarta.transaction.Transactional;

@Repository
public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {

    // Email varun latest OTP find karnyasathi method
    Optional<OtpToken> findByEmail(String email);

    // Juni tokens delete karnyasathi method
    @Transactional    // Database changes secure karnyasathi 
    @Modifying        // Data delete/update karnyasathi
    void deleteByEmail(String email);

}
