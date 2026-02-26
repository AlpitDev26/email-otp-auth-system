package com.JSB.emailOtpVerification.service;

import com.JSB.emailOtpVerification.requests.LoginRequest;
import com.JSB.emailOtpVerification.requests.RegisterRequest;
import com.JSB.emailOtpVerification.responses.LoginResponse;
import com.JSB.emailOtpVerification.responses.RegisterResponse;

import com.JSB.emailOtpVerification.models.Users;
import java.util.List;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);
    String verifyOtp(String email, String otp);
    String resendOtp(String email);
    String initiateForgotPassword(String mail);    // sending the mail after OTP generation
    String resetPassword(String email, String otp, String newPassword);


    LoginResponse login(LoginRequest loginRequest);

    List<Users> getAllUsers();

}
