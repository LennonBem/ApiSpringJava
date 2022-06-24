package com.api.CRUD.service;

import com.api.CRUD.model.DTOs.ProductDTO;
import com.api.CRUD.model.entities.ProductEntiti;
import com.api.CRUD.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Object listarTodosProdutos() throws Exception{

            List<ProductEntiti> products = productRepository.findAll();

            List<ProductDTO> productDTOS = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();

            products.forEach(e-> {
                productDTOS.add(modelMapper.map(e,ProductDTO.class));
            });

            return productDTOS;

    }

    public UUID criarProduto(ProductDTO product) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        ProductEntiti productEntiti = modelMapper.map(product, ProductEntiti.class);
        UUID id = productRepository.save(productEntiti).getId();
        return id;
    }

}
