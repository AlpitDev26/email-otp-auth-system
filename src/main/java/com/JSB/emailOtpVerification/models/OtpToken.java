package com.JSB.emailOtpVerification.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OTP_TOKEN")

public class OtpToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String otp;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isVerified;
}
