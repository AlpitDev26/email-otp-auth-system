package com.JSB.emailOtpVerification.service.IMPL;
import com.JSB.emailOtpVerification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    
    private final JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String to, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Email Verification OTP");
        message.setText("Namaskar!\n\nTumcha OTP ahe: " + otp + "\n\nHa OTP 5 minitansathi valid ahe.");

        mailSender.send(message);


    }
}
