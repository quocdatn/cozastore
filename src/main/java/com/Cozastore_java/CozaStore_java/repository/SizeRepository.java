package com.Cozastore_java.CozaStore_java.repository;

import com.Cozastore_java.CozaStore_java.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {
}
