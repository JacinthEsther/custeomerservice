package com.crowninitiative.customerservice.dtos.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private String email;
    private String accountNumber;
    private BigDecimal tariff;
}
