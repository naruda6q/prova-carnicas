package com.carnicas.prova.repository;

import com.carnicas.prova.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query(value = "SELECT s.id,s.clientId,s.productId,s.saleTime,s.units FROM Sales s WHERE CAST(s.saleTime AS DATE) BETWEEN :fromDate AND :toDate",nativeQuery = true)
    List<Sales> searchBetweenDates(@Param("fromDate") Date startDate, @Param("toDate") Date endDate);

    @Query(value = "SELECT s.id,s.clientId,s.productId,s.saleTime,s.units FROM Sales s WHERE s.clientId = :clientId", nativeQuery = true)
    List<Sales> searchByClientId(@Param("clientId") Integer clientId);

    @Query(value = "SELECT s.id, s.productId, s.saleTime, s.units, s.clientId, c.name, c.createdDate FROM Sales s INNER JOIN Client c ON s.clientId = c.id WHERE c.createdDate < :createdDate ORDER BY c.id",nativeQuery = true)
    List<Sales> searchByOlderClients(@Param("createdDate") Date createdDate);
}
