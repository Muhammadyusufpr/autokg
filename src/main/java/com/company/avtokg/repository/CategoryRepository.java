package com.company.avtokg.repository;

import com.company.avtokg.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}
