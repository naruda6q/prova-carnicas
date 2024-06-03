package com.carnicas.prova.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class ProductDto {

    private Integer id;
    private String name;
    private Float price;

}
