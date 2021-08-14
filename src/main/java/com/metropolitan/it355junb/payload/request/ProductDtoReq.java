package com.metropolitan.it355junb.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoReq {

    private String name;
    private Double price;
    private Integer quantity;

}
