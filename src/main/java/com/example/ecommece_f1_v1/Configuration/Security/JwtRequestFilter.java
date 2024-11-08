package com.example.ecommece_f1_v1.Configuration.Security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter  extends OncePerRequestFilter {
    private final CustomUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(CustomUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
            String jwtToken = extractJwtToken(request);
            if (jwtToken != null) {
                processAuthentication(request, jwtToken);
            }
            chain.doFilter(request, response);
    }
    private String extractJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.substring(7) : null;
    }
    private void processAuthentication(HttpServletRequest request, String jwtToken) {
        validateToken(jwtToken);
        String username = extractUsername(jwtToken);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            authenticateUser(request, username, jwtToken);
        }
    }
    private void validateToken(String jwtToken) {
        if (jwtTokenUtil.isTokenInvalidated(jwtToken)) {
            throw new RuntimeException("Phiên đăng nhập đã hết hạn!");
        }
    }
    private String extractUsername(String jwtToken) {
        try {
            return jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException | ExpiredJwtException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    private void authenticateUser(HttpServletRequest request, String username, String jwtToken) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            throw new RuntimeException("Phiên đăng nhập của bạn đã kết thúc, vui lòng đăng nhập lại");
        }
    }
}
