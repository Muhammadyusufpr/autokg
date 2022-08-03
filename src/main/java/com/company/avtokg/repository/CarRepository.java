package com.company.avtokg.repository;

import com.company.avtokg.entity.CarEntity;
import com.company.avtokg.entity.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, String> {

    Page<CarEntity> findByCategoryId(Pageable pageable, String categoryId);


    @Query("SELECT c.id as c_id, c.bodyType as c_bodyType," +
            " c.brand as c_brand, c.categoryId as c_categoryId, c.driveUnitType as c_driveUnitType ," +
            " c.fuelType as c_fuelType, c.engine as c_engine, c.model as c_model, c.profileId as c_profileId," +
            " c.stateType as c_stateType, c.steeringWheel as c_steeringWheel, c.priceSOM as c_priceSOM, c.priceUSD as c_priceUSD," +
            " c.weight as  c_weight, c.yearOfIssue as c_yearOfIssue, c.carBoxType as c_carBoxType, " +
            " d.id as d_id, d.color as d_color, d.description as d_description, d.clearedType as d_clearedType," +
            " d.mileage as d_mileage ," +
            " r.id as r_id, r.nameRu as r_nameRu, r.nameEn as r_nameEn, r.nameUz as r_nameUz, r.key as r_key" +
            " FROM CarEntity as c" +
            " INNER JOIN c.description as d" +
            " INNER JOIN c.region as r" +
            " where c.id= :id AND r.visible = TRUE")
    Optional<CarMapper> getCar(@Param("id") String id);

    Page<CarEntity> findByRegionId(Pageable pageable, String regionId);

    Page<CarEntity> findByProfileId(Pageable pageable, String profileId);


}
