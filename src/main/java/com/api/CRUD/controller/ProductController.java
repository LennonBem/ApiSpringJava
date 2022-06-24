package com.api.CRUD.controller;

import com.api.CRUD.model.DTOs.ProductDTO;
import com.api.CRUD.model.entities.ProductEntiti;
import com.api.CRUD.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Object> listarTodosProdutos(){
       try{
           return  ResponseEntity.status(HttpStatus.OK).body(productService.listarTodosProdutos());
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!");
       }
    }

    @PostMapping()
    public ResponseEntity<Object> criarProduto(@RequestBody ProductDTO productDTO){
        try{
            UUID id = productService.criarProduto(productDTO);
            productDTO.setId(id);
            return  ResponseEntity.status(HttpStatus.OK).body(productDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!");
        }
    }

}
