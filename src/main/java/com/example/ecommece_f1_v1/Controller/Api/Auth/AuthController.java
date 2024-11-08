package com.example.ecommece_f1_v1.Controller.Api.Auth;

import com.example.ecommece_f1_v1.Configuration.Security.CustomUserDetailsService;
import com.example.ecommece_f1_v1.Configuration.Security.JwtTokenUtil;
import com.example.ecommece_f1_v1.Controller.EndpointContant.AuthApiPaths;
import com.example.ecommece_f1_v1.Exception.ErrorResponse;
import com.example.ecommece_f1_v1.Model.Request.LoginRequest;
import com.example.ecommece_f1_v1.Model.Response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthApiPaths.BASE_PATH)
public class AuthController {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(AuthApiPaths.AUTH_PATH)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsernameOrEmail());
            if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword()))
                return ErrorResponse.buildErrorResponse("Mật khẩu không chính xác",HttpStatus.BAD_REQUEST);
            String token = jwtTokenUtil.generateToken(userDetails);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setMessage("Đăng nhập thành công");
            loginResponse.setStatus(200);
            return ResponseEntity.ok(loginResponse);
        }catch (Exception e) {
            return ErrorResponse.buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
