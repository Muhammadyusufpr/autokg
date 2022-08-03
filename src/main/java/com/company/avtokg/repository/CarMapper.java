package com.company.avtokg.repository;

import com.company.avtokg.enums.car.*;

import java.time.LocalDate;

public interface CarMapper {

    String getC_id();

    CarBodyType getC_bodyType();

    String getC_brand();

    String getC_categoryId();

    DriveUnitType getC_deriveUtilType();

    FuelType getC_fuelType();

    String getC_engine();

    String getC_model();

    String getC_weight();

    String getC_profileId();

    StateType getC_stateType();

    SteeringWheelType getC_steeringWheel();

    Long getC_priceSOM();

    Long getC_priceUSD();

    LocalDate getC_yearOfIssue();

    String getD_id();

    String getD_color();

    String getD_description();

    CustomsClearedType getD_clearedType();

    Integer getD_mileage();

    CarBoxType getC_carBoxType();


    String getR_id();

    String getR_nameRu();

    String getR_nameUz();

    String getR_nameEn();

    String getR_key();






}
