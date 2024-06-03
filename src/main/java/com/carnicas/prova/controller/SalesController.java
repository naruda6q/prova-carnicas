package com.carnicas.prova.controller;

import com.carnicas.prova.dto.SalesDto;
import com.carnicas.prova.entity.Sales;
import com.carnicas.prova.service.ClientService;
import com.carnicas.prova.service.ProductService;
import com.carnicas.prova.service.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sales")
public class SalesController {
    private SalesService salesService;
    private ClientService clientService;
    private ProductService productService;

    //createSales
    @PostMapping("/create")
    public ResponseEntity<SalesDto> createSale(@RequestBody SalesDto saleDto){
        if((saleDto.getClientDetails() == null) && (saleDto.getProduct() == null)){
            return ResponseEntity.badRequest().build();
        }
        SalesDto savedSale = salesService.createSale(saleDto);
        return new ResponseEntity<>(savedSale,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<SalesDto> findByIdSale(@PathVariable("id") Integer saleId){
        SalesDto salesDto = salesService.findByIdSale(saleId);
        return ResponseEntity.ok(salesDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalesDto>> findAllSales(){
        List<SalesDto> sales = salesService.findAllSales();
        return ResponseEntity.ok(sales);
    }

    //putSales
    @PutMapping("/update/{id}")
    public ResponseEntity<SalesDto> updateSales(@PathVariable("id") Integer saleId, @RequestBody SalesDto updatedSaleDto){
        SalesDto foundSales = salesService.findByIdSale(saleId);
        if(foundSales.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        SalesDto salesDto = salesService.updateSales(saleId,updatedSaleDto);
        return ResponseEntity.ok(salesDto);
    }

    //deleteProduct
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer salesId){
        SalesDto salesDto = salesService.findByIdSale(salesId);
        if(salesDto.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        salesService.deleteSales(salesId);
        return ResponseEntity.ok("Sales deleted succesfully");
    }

    //Querys
    @GetMapping("/searchBetweenDates")
    public List<Sales> searchBetweenDates(@RequestParam String fromDate, @RequestParam String toDate){
        Date sqlFromDate = Date.valueOf(fromDate);
        Date sqlToDate = Date.valueOf(toDate);
        return salesService.searchBetweenDates(sqlFromDate,sqlToDate);
    }

    @GetMapping("/searchByClientId")
    public List<SalesDto> searchByClientId(@RequestParam Integer clientId){
        return salesService.searchByClientId(clientId);
    }

    @GetMapping("/searchByOlderClients")
    public List<SalesDto> searchByOlderClients(@RequestParam String createdDate){
        Date sqlFromDate = Date.valueOf(createdDate);
        return salesService.searchByOlderClients(sqlFromDate);
    }
}
