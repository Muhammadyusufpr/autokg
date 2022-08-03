package com.company.avtokg.dto;

import com.company.avtokg.enums.car.CustomsClearedType;
import com.company.avtokg.enums.car.StateType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DescriptionAndEquipmentDTO {
    private String id;
    private String color;
    private StateType stateType;
    private String mileage; // пробег
    private String description;
    private String carId;
    private CustomsClearedType clearedType; // растаможен

    private LocalDateTime createdDate;
}
