package com.example.ecommece_f1_v1.Entity.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "login_at")
    private Timestamp loginAt;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private ProfileEntity profileId;
    private boolean active;
}
