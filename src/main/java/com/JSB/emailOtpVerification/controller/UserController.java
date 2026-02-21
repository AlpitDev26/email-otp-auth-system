package com.JSB.emailOtpVerification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {
        // Token madhun jo email save kela hota, to baher kadhne
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        return ResponseEntity.ok("Namaskar! Tumche email ahe: " + email + ". Ha message tumhala JWT Token valid asalya-nantarch distoy!");
    }
}
