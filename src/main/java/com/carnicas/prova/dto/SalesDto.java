package com.carnicas.prova.dto;

import com.carnicas.prova.entity.Client;
import com.carnicas.prova.entity.Product;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class SalesDto {

    private Integer id;
    private Timestamp saleTime;
    private Float units;

    private Client clientDetails;

    private Product product;

}
