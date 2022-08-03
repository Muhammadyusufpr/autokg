package com.company.avtokg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarFullDTO {
    private CarDTO carDTO;
    private GenerationDTO generationDTO;
    private DescriptionAndEquipmentDTO equipmentDTO;

}
