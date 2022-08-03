package com.company.avtokg.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
}
