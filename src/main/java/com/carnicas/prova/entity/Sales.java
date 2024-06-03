package com.carnicas.prova.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="sales")

public class Sales implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Madrid")
    @Column(name = "saleTime")
    private Timestamp saleTime;
    @Column(name = "units")
    private Float units;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JsonIgnore
    @JoinColumn(name = "clientId",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client clientDetails;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
    @JsonIgnore
    @JoinColumn(name="productId")
    private Product product;


}