package com.api.CRUD.repository;

import com.api.CRUD.model.entities.CategoryEntiti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntiti,Long> {

}
