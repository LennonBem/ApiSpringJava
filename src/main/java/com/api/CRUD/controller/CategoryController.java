package com.api.CRUD.controller;

import com.api.CRUD.model.DTOs.CategoryDTO;
import com.api.CRUD.model.entities.CategoryEntiti;
import com.api.CRUD.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<Object> criarCategoria(@RequestBody List<CategoryDTO> categoryDTO){
        try{
            List<CategoryEntiti> categoryEntiti = categoryService.criarCategoria(categoryDTO);
            categoryDTO.clear();
            categoryEntiti.forEach(e -> {
                categoryDTO.add(modelMapper.map(e, CategoryDTO.class));
            });
            return  ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!");
        }
    }

    @GetMapping()
    public Object listarTodasCategorias() {

        try {
      List<CategoryEntiti> categoryEntities = categoryService.buscarCategorias();

            return ResponseEntity.status(HttpStatus.OK).body(categoryEntities);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!");
        }
    }




}

