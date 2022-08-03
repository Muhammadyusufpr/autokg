package com.company.avtokg.controller;


import com.company.avtokg.dto.CarDTO;
import com.company.avtokg.dto.CarFullDTO;
import com.company.avtokg.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = "Car")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/car")
public class CarController {
    private final CarService carService;

    @ApiOperation(value = "create", notes = "Method used for create car")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/{attachId}")
    public ResponseEntity<CarFullDTO> create(@RequestBody CarFullDTO dto,
                                             @PathVariable("attachId") String attachId) {

        log.info("create: {}{}{}", dto, CarController.class, attachId);
//        return ResponseEntity.ok(carService.create(dto, attachId));
        return null;
    }


    @ApiOperation(value = "update", notes = "Method used for update car")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") String id,
                                          @RequestBody @Valid CarDTO dto) {
        log.info("update: {}{}", id, CarController.class);
        return ResponseEntity.ok(carService.update(id, dto));
    }

    @ApiOperation(value = "get by id", notes = "Method used for get by id to full dto")
    @GetMapping("/getBy/{id}")
    public ResponseEntity<?> getByIdToFullDTO(@PathVariable String id) {
        log.info("getById to full DTO {}{}", id, CarController.class);
        return ResponseEntity.ok(carService.toFullInfoDTO(id));
    }

    @ApiOperation(value = "get by id", notes = "Method used for get by id to dto ")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getByIdToDTO(@PathVariable String id) {
        log.info("get by id to DTO: {}{}", id, CarController.class);
        return ResponseEntity.ok(carService.getBYId(id));
    }

    @ApiOperation(value = "get pagination", notes = "Method used for get pagination by categoryId")
    @GetMapping("/list/{categoryId}")
    public ResponseEntity<?> getPaginationByCategoryId(@PathVariable("categoryId") String categoryId,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "3") int size) {
        log.info("carDTO: {}", CarController.class);
        return ResponseEntity.ok(carService.getPaginationByCategoryId(size, page, categoryId));
    }


    @ApiOperation(value = "get by region id", notes = "Method used for get pagination by region id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaginationByRegionId(@PathVariable("id") String id,
                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "3") int size) {
        log.info("Car get pagination: {}", id);
        return ResponseEntity.ok(carService.getPaginationByRegionId(size, page, id));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "get pagination by id", notes = "Method used for get pagination by profileId")
    @GetMapping("/list/{id}")
    public ResponseEntity<?> getPagination(@PathVariable("id") String id,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "3") int size) {
        log.info("carDTO: {}", CarController.class);
        return ResponseEntity.ok(carService.getById(id, size, page));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "delete by id", notes = "method used for delete car by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        log.info("delete : {}{}", id, CarController.class);
        return ResponseEntity.ok(carService.delete(id));
    }


}
