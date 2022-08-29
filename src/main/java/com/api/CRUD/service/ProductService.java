package com.api.CRUD.service;

import com.api.CRUD.model.DTOs.Category.CategoryResponse;
import com.api.CRUD.model.DTOs.Product.ProductResponse;
import com.api.CRUD.model.DTOs.ProductDTO;
import com.api.CRUD.model.entities.ProductEntiti;
import com.api.CRUD.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProductResponse> listarTodosProdutos(int page, int size) throws Exception{

        Pageable pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "id");

            Page<ProductEntiti> products = productRepository.findAll(pageRequest);



            Page<ProductResponse> productResponse = products.map(c -> {
               var categoryResponse =  modelMapper.map(c.getCategory(), CategoryResponse.class);
               return new ProductResponse(c.getId(), c.getName(), c.getPrice(), categoryResponse);
            }
            );


        return productResponse;
    }


    public Optional<ProductEntiti> buscarPorID(Long id)throws Exception{
     Optional<ProductEntiti> productEntiti = productRepository.findById(id);
     return productEntiti;
    }

    public List<ProductDTO> filterByName(String name) throws Exception{
        String nameLowerCase = name.toLowerCase();
        String nameFormatConsult = String.format("%s%s%s","%", nameLowerCase, "%" );
        System.out.println(nameFormatConsult);

        List<ProductEntiti> productEntiti = productRepository.findByNameContains(nameFormatConsult);

        List<ProductDTO> productDTO = new ArrayList<>();
        productEntiti.forEach(e-> {
            productDTO.add(modelMapper.map(e, ProductDTO.class));

        });

        return productDTO;
    }

    public ProductEntiti criarProduto(ProductDTO product) throws Exception {


       var productEntiti =  modelMapper.map(product, ProductEntiti.class);


         return productRepository.save(productEntiti);

    }

    public ProductEntiti atualizarProduto(ProductDTO product) throws Exception{
        ProductEntiti productEntiti = modelMapper.map(product, ProductEntiti.class);
        return productRepository.save(productEntiti);
    }

    public void excluirProduto(Long id)throws Exception{
        productRepository.deleteById(id);
    }



}
