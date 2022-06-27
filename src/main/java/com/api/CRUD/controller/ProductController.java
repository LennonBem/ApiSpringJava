package com.api.CRUD.controller;

import com.api.CRUD.model.DTOs.ProductDTO;
import com.api.CRUD.model.entities.ProductEntiti;
import com.api.CRUD.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/products")
public class ProductController {



    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;




    @GetMapping()
    public ResponseEntity<Object> listarTodosProdutos(){
       try{
           List<ProductDTO> produtos = productService.listarTodosProdutos();

               return  ResponseEntity.status(HttpStatus.OK).body(produtos);

       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!");
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        try {
            Optional product = productService.buscarPorID(id);
            if (!product.isEmpty()) {
                ProductDTO productDTO = modelMapper.map(product.get(), ProductDTO.class);
               return ResponseEntity.status(HttpStatus.OK).body(productDTO);

            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto não cadastrado!");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> criarProduto(@RequestBody List<ProductDTO> productDTO){
        try{
            List<ProductEntiti> productEntiti = productService.criarProduto(productDTO);
            productDTO.clear();
            productEntiti.forEach(e -> {
                productDTO.add(modelMapper.map(e, ProductDTO.class));
            });
            return  ResponseEntity.status(HttpStatus.OK).body(productDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!");
        }
    }

    @PutMapping()
    public ResponseEntity<Object> atualizarProduto(@RequestBody ProductDTO productDTO){
        try{
            ProductEntiti productEntiti = productService.atualizarProduto(productDTO);
            return  ResponseEntity.status(HttpStatus.OK).body(productDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> ExcluirProduto(@PathVariable Long id){
        try{
            productService.excluirProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto Excluido com Sucesso!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto não existe!");
        }


    }

}
