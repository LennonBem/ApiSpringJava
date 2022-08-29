package com.api.CRUD.service;

import com.api.CRUD.model.DTOs.CategoryDTO;
import com.api.CRUD.model.entities.CategoryEntiti;
import com.api.CRUD.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryEntiti> criarCategoria(List<CategoryDTO> categoryDTO) throws Exception {
        List<CategoryEntiti> categoryEntiti = new ArrayList<>();
        categoryDTO.forEach(e-> {
            categoryEntiti.add(modelMapper.map(e, CategoryEntiti.class));

        });
        return categoryRepository.saveAll(categoryEntiti);

    }

    public List<CategoryEntiti> buscarCategorias() throws Exception {
    return categoryRepository.findAll();

    }

    }


