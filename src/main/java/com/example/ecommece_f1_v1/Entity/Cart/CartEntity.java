package com.example.ecommece_f1_v1.Entity.Cart;

import com.example.ecommece_f1_v1.Entity.Product.SkuEntity;
import com.example.ecommece_f1_v1.Entity.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sku_id")
    private SkuEntity skuId;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;
    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private Timestamp updatedAt;
}
