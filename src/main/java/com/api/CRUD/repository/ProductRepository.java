package com.api.CRUD.repository;

import com.api.CRUD.model.entities.ProductEntiti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntiti,Long> {
}
