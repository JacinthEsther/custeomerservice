package com.crowninitiative.customerservice.models;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BillingDetails {
    private String accountNumber;
    private BigDecimal tariff;

}
