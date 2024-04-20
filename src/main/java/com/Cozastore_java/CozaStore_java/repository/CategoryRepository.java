package com.Cozastore_java.CozaStore_java.repository;

import com.Cozastore_java.CozaStore_java.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
