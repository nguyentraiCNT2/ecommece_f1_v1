package com.example.ecommece_f1_v1.Configuration.Security;

import com.example.ecommece_f1_v1.Entity.User.UserEntity;
import com.example.ecommece_f1_v1.Repository.User.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user entity and validate it
        UserEntity userEntity = userRepository.findByUsernameOrEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

        if (!userEntity.isActive())
            throw new RuntimeException("Tài khoản của bạn đã bị khóa");

        return User.withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
    }
}
