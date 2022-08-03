package com.company.avtokg.dto;

import com.company.avtokg.entity.AttachEntity;
import com.company.avtokg.enums.car.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CarDTO {
    private String id;
    private String weight;
    private String profileId;
    private AttachEntity image;
    private String engine;
    private LocalDate yearOfIssue;
    private String model;
    private String brand;
    private String attachId;
    private String regionId;

    private DriveUnitType driveUnitType;
    private CarBodyType carBodyType;
    private StateType stateType;
    private FuelType fuelType;
    private SteeringWheelType steeringWheel;// руль
    private Long priceSOM;
    private Long priceUSD;


    private LocalDateTime createdDate;

    public CarDTO(String id, LocalDateTime createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }
}
