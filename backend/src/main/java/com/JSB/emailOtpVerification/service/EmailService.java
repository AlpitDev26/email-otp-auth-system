package com.JSB.emailOtpVerification.service;

public interface EmailService {
    void sendOtpEmail(String to, String otp);
    
}
