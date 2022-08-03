package com.company.avtokg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerationDTO {
    private String id;
    private String model;
    private String date;
    private String attachId;
    private String carId;

    private LocalDateTime createdDate;

}
