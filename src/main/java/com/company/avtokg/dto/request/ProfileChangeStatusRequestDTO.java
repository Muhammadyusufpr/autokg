package com.company.avtokg.dto.request;

import com.company.avtokg.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileChangeStatusRequestDTO {
    ProfileStatus status;
}
