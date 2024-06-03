package com.carnicas.prova.mapper;

import com.carnicas.prova.dto.SalesDto;
import com.carnicas.prova.entity.Sales;

public class SalesMapper {

    public static SalesDto mapToSalesDto(Sales sale){
        return new SalesDto(
                sale.getId(),
                sale.getSaleTime(),
                sale.getUnits(),
                sale.getClientDetails(),
                sale.getProduct()
        );
    }

    public static Sales mapToSale(SalesDto saleDto){
        return new Sales(
                saleDto.getId(),
                saleDto.getSaleTime(),
                saleDto.getUnits(),
                saleDto.getClientDetails(),
                saleDto.getProduct()
        );
    }
}
