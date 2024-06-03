package com.carnicas.prova.service.impl;

import com.carnicas.prova.dto.SalesDto;
import com.carnicas.prova.entity.Client;
import com.carnicas.prova.entity.Product;
import com.carnicas.prova.mapper.SalesMapper;
import com.carnicas.prova.repository.ClientRepository;
import com.carnicas.prova.repository.ProductRepository;
import com.carnicas.prova.repository.SalesRepository;
import com.carnicas.prova.entity.Sales;
import com.carnicas.prova.exception.ResourceNotFoundException;
import com.carnicas.prova.service.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;

    @Override
    public SalesDto createSale(SalesDto saleDto){

        Client client;
        Product product;

        if(saleDto.getClientDetails().getId() == null){
            client = clientRepository.save(saleDto.getClientDetails());
        } else {
            Integer clientId = saleDto.getClientDetails().getId();
            client = clientRepository.findById(clientId).orElseThrow(
                    () -> new ResourceNotFoundException("Client is not exists")
            );
        }

        if(saleDto.getProduct().getId() == null){
            product = productRepository.save(saleDto.getProduct());
        } else {
            Integer productId = saleDto.getProduct().getId();
            product = productRepository.findById(productId).orElseThrow(
                    () -> new ResourceNotFoundException("Product is not exists")
            );
        }

        Sales sale = new Sales(
                saleDto.getId(),
                saleDto.getSaleTime(),
                saleDto.getUnits(),
                client,
                product
        );

        Sales savedSale = salesRepository.save(sale);
        return SalesMapper.mapToSalesDto(savedSale);
    }

    @Override
    public SalesDto updateSales(Integer saleId, SalesDto salesDto){

        Sales sale = salesRepository.findById(saleId).orElseThrow(
                () -> new ResourceNotFoundException("Sales is not exists")
        );

        if(salesDto.getSaleTime() != null){
            sale.setSaleTime(salesDto.getSaleTime());
        }
        if(salesDto.getUnits() != null){
            sale.setUnits(salesDto.getUnits());
        }
        if((salesDto.getClientDetails() != null) && (salesDto.getClientDetails().getId() != null)){
            Integer clientId = salesDto.getClientDetails().getId();
            Client client = clientRepository.findById(clientId).orElseThrow(
                    () -> new ResourceNotFoundException("Client is not exists")
            );
            sale.setClientDetails(client);
        }

        if((salesDto.getProduct() != null) && (salesDto.getProduct().getId() != null)){
            Integer productId = salesDto.getProduct().getId();
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new ResourceNotFoundException("Product is not exists")
            );
            sale.setProduct(product);
        }

        Sales updateSale = salesRepository.save(sale);

        return SalesMapper.mapToSalesDto(updateSale);
    }

    @Transactional(readOnly = true)
    @Override
    public SalesDto findByIdSale(Integer id) {
        Sales sale = salesRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Sale is not exists")
                );
        return SalesMapper.mapToSalesDto(sale);
    }

    @Override
    public List<SalesDto> findAllSales(){
        List<Sales> sales = salesRepository.findAll();
        return sales.stream().map(sale -> SalesDto.builder()
                        .id(sale.getId())
                        .saleTime(sale.getSaleTime())
                        .units(sale.getUnits())
                        .clientDetails(sale.getClientDetails())
                        .product(sale.getProduct())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSales(Integer id) {
        Sales sale = salesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sale is not exists")
        );
        salesRepository.deleteById(id);
    }

    @Override
    public List<Sales> searchBetweenDates(Date fromDate, Date toDate){
        return salesRepository.searchBetweenDates(fromDate, toDate);
    }

    @Override
    public List<SalesDto> searchByClientId(Integer clientId){
        List<Sales> sales = salesRepository.searchByClientId(clientId);
        return sales.stream().map(sale -> SalesDto.builder()
                        .id(sale.getId())
                        .saleTime(sale.getSaleTime())
                        .units(sale.getUnits())
                        .product(sale.getProduct())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesDto> searchByOlderClients(Date createdDate){
        List<Sales> sales = salesRepository.searchByOlderClients(createdDate);
        return sales.stream().map(sale -> SalesDto.builder()
                        .id(sale.getId())
                        .saleTime(sale.getSaleTime())
                        .units(sale.getUnits())
                        .clientDetails(sale.getClientDetails())
                        .product(sale.getProduct())
                        .build())
                .collect(Collectors.toList());
    }
}
