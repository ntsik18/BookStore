package com.nini.bookstore.orderservice.domain.models;

import jakarta.validation.constraints.NotBlank;

public record Address(
       @NotBlank(message = "Address line1 is required") String line1,
        String line2,
       @NotBlank(message = "city is required") String city,
       @NotBlank(message = "state is required")  String state,
       @NotBlank(message = "Zip code is required") String zip,
       @NotBlank(message = "Country is required") String country
) {
}
