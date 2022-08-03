package com.company.avtokg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private String profileId;
    private String nameUz;
    private String nameRu;
    private String nameEn;

    @NotBlank(message = "name is won't be Null")
    private String name;

    private String id;
    private String key;
    private LocalDateTime createdDate;

    public RegionDTO( String id, String key) {
        this.id = id;
        this.key = key;
    }
}
