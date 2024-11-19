package com.nini.bookstore.orderservice.domain.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public record OrderItem(
        @NotBlank(message = "Code is required") String code,
        @NotBlank(message = "Name is required")  String name,
        @NotNull (message = "Price is required") BigDecimal price,
        @NotNull @Min(1) Integer quantity
) {
}
