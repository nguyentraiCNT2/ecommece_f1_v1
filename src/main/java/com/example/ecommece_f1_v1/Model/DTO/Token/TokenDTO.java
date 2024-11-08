package com.example.ecommece_f1_v1.Model.DTO.Token;

import com.example.ecommece_f1_v1.Model.DTO.User.UserDTO;

import java.sql.Timestamp;

public class TokenDTO {
    private Long id;
    private String token;
    private Timestamp expires;
    private Timestamp created;
    private UserDTO userId;
}
