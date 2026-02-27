package com.JSB.emailOtpVerification.controller;

import com.JSB.emailOtpVerification.requests.LoginRequest;
import com.JSB.emailOtpVerification.requests.RegisterRequest;
import com.JSB.emailOtpVerification.requests.VerifyOtpRequest;
import com.JSB.emailOtpVerification.responses.LoginResponse;
import com.JSB.emailOtpVerification.responses.RegisterResponse;
import com.JSB.emailOtpVerification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = userService.register(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @org.springframework.web.bind.annotation.GetMapping("/status")
    public String status() {
        return "Server is running!";
    }

    @org.springframework.web.bind.annotation.GetMapping("/users")
    public java.util.List<com.JSB.emailOtpVerification.models.Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
        String result = userService.verifyOtp(verifyOtpRequest.getEmail(), verifyOtpRequest.getOtp());

        if (result.equals("User Verified Successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestParam String email) {
        String result = userService.resendOtp(email);
        return ResponseEntity.ok(result);
    }

        // 1. Forgot Password - OTP Pathavnyasathi
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String result = userService.initiateForgotPassword(email);
        return ResponseEntity.ok(result);
    }

    // 2. Reset Password - OTP Verify karun Navin Password set karnyasathi
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword) {
        String result = userService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok(result);
    }


}
