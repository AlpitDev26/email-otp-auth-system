package com.JSB.emailOtpVerification.service;

import java.time.LocalDateTime;
import java.util.Random;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.JSB.emailOtpVerification.models.OtpToken;
import com.JSB.emailOtpVerification.repository.OtpTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class OtpService {

    private final OtpTokenRepository otpTokenRepository;

    private final EmailService emailService;

    // 1. Otp Generate, Save ani email pathavne
    public String generateandSaveOtp(String email) {

        // 1.2. june tokens delete karne (otp delete karne database madhun)
        otpTokenRepository.deleteByEmail(email);

        // 1.3. 6-digit random number generate karne
        // nextInt(1000000) mahnje 000000 to 999999 paryant number
        String otp = String.format("%06d", new Random().nextInt(1000000));

        // 4. Otp Token cha object banavne
        OtpToken otpToken = OtpToken.builder()
                .otp(otp)
                .email(email)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5)) // 5 min nantr expire honar
                .isVerified(false)
                .build();

        // 1.5 Otp Database madhe save karne
        otpTokenRepository.save(otpToken);

        // 1.6Actual Email pathavne
        emailService.sendOtpEmail(email, otp);
        return otp;
    }

    // 2. OTP Verify karaych logic

    public boolean verifyOtp(String email, String otp) {

        Optional<OtpToken> otpTokenOpt = otpTokenRepository.findByEmail(email);

        if(otpTokenOpt.isPresent()){
            OtpToken otpToken = otpTokenOpt.get();

            // otp check karaych ki correct ahe ki nahi kivha expire halay ki nahi 
            if(otpToken.getOtp().equals(otp) && otpToken.getExpiresAt().isAfter(LocalDateTime.now())){

                // otp delete zhalyavr token delete karne 
                otpTokenRepository.deleteByEmail(email);
                return true;
            }
        }
        return false;
    }

    public String validateOtp(String email, String otp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateOtp'");
    }

    public void deleteOtp(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOtp'");
    }
}
