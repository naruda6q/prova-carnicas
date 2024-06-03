package com.carnicas.prova.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class ClientDto {

    private Integer id;
    private String name;
    private Date createdDate;

    //private List<Sales> sales = new ArrayList<>();

}
