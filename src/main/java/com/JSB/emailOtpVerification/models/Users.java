package com.JSB.emailOtpVerification.models;


import jakarta.persistence.*;
import lombok.*;


// Requirements from the users to Spring Boot

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
@Entity

public class Users{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean isVerified;
}

