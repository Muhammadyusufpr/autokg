package com.company.avtokg.controller;

import com.company.avtokg.dto.GenerationDTO;
import com.company.avtokg.service.GenerationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = "Generation")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/generation") // поколение
public class GenerationController {

    private final GenerationService generationService;


    @ApiOperation(value = "create", notes = "Method used for create generation")
    @PostMapping("/{carId}")
    public ResponseEntity<GenerationDTO> create(@PathVariable("carId") String carId,
                                                @RequestBody @Valid GenerationDTO dto) {
        log.info("create: {}{}{}", carId, dto, GenerationController.class);
        return ResponseEntity.ok(generationService.create(dto, carId));

    }

    @ApiOperation(value = "update", notes = "Method used for update generation")
    @PutMapping("/{carId}")
    public ResponseEntity<?> update(@PathVariable String carId,
                                    @RequestBody @Valid GenerationDTO dto) {
        log.info("update: {}{}", carId, dto);
        return ResponseEntity.ok(generationService.update(carId, dto));
    }

    @ApiOperation(value = "get", notes = "Method used for get")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        log.info("get by id: {}{}", id, GenerationController.class);
        return ResponseEntity.ok(generationService.getById(id));
    }

    @ApiOperation(value = "delete", notes = "Method used for delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("delete: {}{}", id, GenerationController.class);
        return ResponseEntity.ok(generationService.delete(id));
    }



}
