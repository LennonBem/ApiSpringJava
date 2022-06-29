package com.api.CRUD.service;

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

    public Page<ProductDTO> listarTodosProdutos(int page, int size) throws Exception{

        Pageable pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "id");

            Page<ProductEntiti> products = productRepository.findAll(pageRequest);

            Page<ProductDTO> productDTOS = products.map(c -> new ProductDTO(c.getId(),c.getName(),c.getPrice()));

        return productDTOS;
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

    public List<ProductEntiti> criarProduto(List<ProductDTO> product) throws Exception {
        List<ProductEntiti> productEntiti = new ArrayList<>();
        product.forEach(e-> {
            productEntiti.add(modelMapper.map(e, ProductEntiti.class));

        });
         return productRepository.saveAll(productEntiti);

    }

    public ProductEntiti atualizarProduto(ProductDTO product) throws Exception{
        ProductEntiti productEntiti = modelMapper.map(product, ProductEntiti.class);
        return productRepository.save(productEntiti);
    }

    public void excluirProduto(Long id)throws Exception{
        productRepository.deleteById(id);
    }



}
