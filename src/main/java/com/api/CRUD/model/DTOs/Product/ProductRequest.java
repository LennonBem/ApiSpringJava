package com.api.CRUD.model.DTOs.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

  private String name;
  private Float price;
  private Long idCategory;
  private byte[] foto;

}
