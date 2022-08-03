package com.company.avtokg.dto.response;

import com.company.avtokg.dto.request.ProfileRequestDTO;
import com.company.avtokg.entity.AttachEntity;
import com.company.avtokg.enums.ProfileRole;
import com.company.avtokg.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponseDTO extends ProfileRequestDTO {
    private String id;
    private ProfileRole role;
    private ProfileStatus status;
    private AttachEntity image;

    private LocalDateTime createdDate = LocalDateTime.now();

    private String jwt;
}
