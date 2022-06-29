package com.api.CRUD.repository;

import com.api.CRUD.model.entities.ProductEntiti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntiti,Long> {



    @Query(
            value = "SELECT p FROM ProductEntiti p WHERE LOWER(p.name) LIKE :nameFormatConsult")
    List<ProductEntiti> findByNameContains(@Param("nameFormatConsult") String nameFormatConsult);

}
