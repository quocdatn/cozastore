package com.Cozastore_java.CozaStore_java.entity.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class OrderDetailIds implements Serializable {
    @Column(name = "product_id")
    private int productId;

    @Column(name = "order_id")
    private int orderId;

    public OrderDetailIds(){}

    public OrderDetailIds(int productId, int orderId){
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
