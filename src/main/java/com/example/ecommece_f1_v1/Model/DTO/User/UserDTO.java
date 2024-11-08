package com.example.ecommece_f1_v1.Model.DTO.User;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    @CreatedDate
    private Timestamp createdAt;
    private Timestamp loginAt;
    private ProfileDTO profileId;
    private boolean active;
}
