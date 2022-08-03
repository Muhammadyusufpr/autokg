package com.company.avtokg.controller;


import com.company.avtokg.dto.AuthDTO;
import com.company.avtokg.dto.RegistrationDto;
import com.company.avtokg.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@Api(tags = "For Authorization")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "registration", notes = "Method for registration")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationDto dto) {
        log.info("registration: {}", dto);
        authService.registration(dto);
        return ResponseEntity.ok().build();
    }



    @ApiOperation(value = "login", notes = "Method used for login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO dto) {
        log.info("login: {}", dto);
        return ResponseEntity.ok(authService.login(dto));
    }

}
