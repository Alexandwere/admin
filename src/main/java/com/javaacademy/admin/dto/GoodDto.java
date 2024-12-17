package com.javaacademy.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodDto {
    private String name;
    @JsonProperty("new_price")
    private BigDecimal newPrice;
}
