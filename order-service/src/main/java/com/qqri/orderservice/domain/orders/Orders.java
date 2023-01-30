package com.qqri.orderservice.domain.orders;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Data
@NoArgsConstructor
@Entity
public class Orders{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String sellerId;

    @Column(nullable = false)
    private String buyerId;

    @Column(nullable = false, unique = true)
    private String orderId;

    @Column(nullable = false,updatable = false,insertable = false)
    @ColumnDefault(value ="CURRENT_TIMESTAMP")
    private Date createdAt;

    @Builder
    public Orders(Long id, String productId, Integer totalPrice, String sellerId,String buyerId, String orderId, Date createdAt) {
        this.id = id;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.orderId = orderId;
        this.createdAt = createdAt;
    }

}
