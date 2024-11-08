package com.example.ecommece_f1_v1.Model.DTO.Favorites;

import com.example.ecommece_f1_v1.Model.DTO.Product.SkuDTO;
import com.example.ecommece_f1_v1.Model.DTO.User.UserDTO;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class FavoritesDTO {
    private Long id;
    private UserDTO userId;
    private SkuDTO skuId;
    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp updatedAt;
}
