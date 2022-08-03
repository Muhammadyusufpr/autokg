package com.company.avtokg.controller;

import com.company.avtokg.dto.DescriptionAndEquipmentDTO;
import com.company.avtokg.enums.ProfileRole;
import com.company.avtokg.service.DescriptionAndEquipmentService;
import com.company.avtokg.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Api(tags = "DescriptionAndEquipment")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/description_and_equipment")
public class DescriptionAndEquipmentController {
    private final DescriptionAndEquipmentService descriptionAndEquipmentService;


    @ApiOperation(value = "create", notes = "Method used for create descriptions")
    @PostMapping("/{carId}")
    public ResponseEntity<?> create(@PathVariable("carId") String carId,
                                    @RequestBody @Valid DescriptionAndEquipmentDTO dto) {
        log.info("create description: {}{}{}", carId, dto, DescriptionAndEquipmentController.class);
        return ResponseEntity.ok(descriptionAndEquipmentService.create(carId, dto));
    }

    @ApiOperation(value = "update", notes = "Method used for update")
    @PutMapping("/{carId}")
    public ResponseEntity<?> update(@PathVariable("carId") String carId,
                                    @RequestBody @Valid DescriptionAndEquipmentDTO dto) {
        log.info("update: {}{}{}", carId, dto, DescriptionAndEquipmentController.class);
        return ResponseEntity.ok(descriptionAndEquipmentService.update(carId, dto));
    }


    @ApiOperation(value = "delete", notes = "Method used for delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("method delete: {}{}", id, DescriptionAndEquipmentController.class);
        return ResponseEntity.ok(descriptionAndEquipmentService.delete(id));
    }


    // TODO: 22.06.2022 todo get by id


}
