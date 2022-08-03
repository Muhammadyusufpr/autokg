package com.company.avtokg.repository;

import com.company.avtokg.entity.CarEntity;
import com.company.avtokg.entity.GenerationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GenerationRepository extends JpaRepository<GenerationEntity, String> {

    @Query("select g from GenerationEntity g where g.carId = ?1")
    Optional<GenerationEntity> findByCarId(String carId);


}
