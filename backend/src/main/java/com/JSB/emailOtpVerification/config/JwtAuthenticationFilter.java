package com.JSB.emailOtpVerification.config;

import com.JSB.emailOtpVerification.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 1. Header nasel kiwa Bearer ne suru hot nasel tar ignore karne
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Token baher kadhne (Bearer nantrcha bhag)
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractEmail(jwt);

        // 3. Jar User milala ani to adhihi authenticated nasel tar...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Ithe apan sadhya sathi ek dummy authentication set kartoय
            // (Pudhe apan UserDetails vaprun he jasti secure karu)
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userEmail, null, new ArrayList<>()
            );
            
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        
        filterChain.doFilter(request, response);
    }
}
