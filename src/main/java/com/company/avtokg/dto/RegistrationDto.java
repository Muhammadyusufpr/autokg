package com.company.avtokg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDto {
    @NotBlank
    @Size(min = 3, max = 25, message = "name required")
    private String name;

    @NotBlank
    @Size(min = 3, max = 25, message = "surname required")
    private String surname;


    private String email;

    @NotBlank(message = "password required")
    @Size(min = 6, max = 20, message = "password required")
    private String password;

    private String phone;

}

