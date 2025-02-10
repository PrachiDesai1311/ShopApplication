package com.example.microservice.order.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, String stuCode, BigDecimal price, Integer quantity) {
}
