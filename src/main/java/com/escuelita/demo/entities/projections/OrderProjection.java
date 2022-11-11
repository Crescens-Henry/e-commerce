package com.escuelita.demo.entities.projections;

public interface OrderProjection {

    Long getId();

    String getDate();
    
    Long getClientId();

    Long getShippingId();

    Long getBillId();

    Long getStatusOrderId();

    String getProductName();
}
