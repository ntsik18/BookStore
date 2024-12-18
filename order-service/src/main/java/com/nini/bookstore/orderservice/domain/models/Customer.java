package com.nini.bookstore.orderservice.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Customer(
      @NotBlank (message = "Customer Name is required") String name,
      @NotBlank (message = "Customer Email is required") @Email String email,
      @NotBlank (message = "Customer Phone is required") @NotBlank String phone

) {
}
