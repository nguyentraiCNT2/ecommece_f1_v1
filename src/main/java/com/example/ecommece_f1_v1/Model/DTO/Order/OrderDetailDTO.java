package com.example.ecommece_f1_v1.Model.DTO.Order;

import com.example.ecommece_f1_v1.Model.DTO.Product.SkuDTO;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OrderDetailDTO {
    private Long id;
    private OrderDTO orderId;
    private SkuDTO skuId;
    private int quantity;

}
