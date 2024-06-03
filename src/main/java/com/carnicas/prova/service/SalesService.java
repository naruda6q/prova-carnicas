package com.carnicas.prova.service;

import com.carnicas.prova.dto.SalesDto;
import com.carnicas.prova.entity.Sales;

import java.sql.Date;
import java.util.List;

public interface SalesService {

    SalesDto findByIdSale(Integer id);

    List<SalesDto> findAllSales();

    SalesDto createSale(SalesDto salesDto);

    SalesDto updateSales(Integer salesId, SalesDto salesDto);

    void deleteSales(Integer id);

    List<Sales> searchBetweenDates(Date fromDate, Date toDate);

    List<SalesDto> searchByClientId(Integer clientId);

    List<SalesDto> searchByOlderClients(Date createdDate);
}
