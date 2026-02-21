package com.JSB.emailOtpVerification.service.IMPL;

import com.JSB.emailOtpVerification.models.Users;
import com.JSB.emailOtpVerification.repository.UsersRepository;
import com.JSB.emailOtpVerification.requests.LoginRequest;
import com.JSB.emailOtpVerification.requests.RegisterRequest;
import com.JSB.emailOtpVerification.responses.LoginResponse;
import com.JSB.emailOtpVerification.responses.RegisterResponse;
import com.JSB.emailOtpVerification.service.JwtService;
import com.JSB.emailOtpVerification.service.OtpService;
import com.JSB.emailOtpVerification.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final OtpService otpService;
    private final PasswordEncoder passwordEncoder;

    // ... Imports ...
    private final JwtService jwtService; // He inject kara constructor madhe

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Users user = usersRepository.findByEmail(loginRequest.getEmail());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            // --- IMPORTANT CHECK ---
            if (!user.isVerified()) {
                throw new RuntimeException("Pahila Email verify kara!");
            }

            // Token generate karne
            String token = jwtService.generateToken(user.getEmail());

            return LoginResponse.builder()
                    .token(token)
                    .email(user.getEmail())
                    .build();
        }

        throw new RuntimeException("Email kiwa Password chukicha ahe!");
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Users isUserExisting = usersRepository.findByEmail(registerRequest.getEmail());
        if (isUserExisting != null && isUserExisting.isVerified()) {
            throw new RuntimeException("User Already Registered...");
        }

        // Using builder method to build user
        Users users = Users.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        // to save data taken by the users
        usersRepository.save(users);

        // User save zhalyanantr lagech opt pathavne
        otpService.generateandSaveOtp(users.getEmail());

        // to show the response to the user
        RegisterResponse response = RegisterResponse.builder()
                .username(users.getUsername())
                .email(users.getEmail())
                .build();
        return response;
    }

    @Override
    public java.util.List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public String verifyOtp(String email, String otp) {
        boolean isVerified = otpService.verifyOtp(email, otp);

        if (isVerified) {
            Users user = usersRepository.findByEmail(email);
            user.setVerified(true);
            usersRepository.save(user);
            return "User Verified Successfully";
        }
        return "Invalid OTP or OTP Expired";
    }

    @Override
    public String resendOtp(String email) {
        Users user = usersRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User Not Found");
        }

        // Jar user adhi-ch verified asel tar OTP pathavnyachi garaj nahi
        if (user.isVerified()) {
            return "User is already Verified";
        }

        // Navin OTP generate karne ani pathavne
        otpService.generateandSaveOtp(email);

        return "New OTP is sent to your email";
    }

}
