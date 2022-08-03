package com.company.avtokg.controller;

import com.company.avtokg.dto.request.ProfileChangeStatusRequestDTO;
import com.company.avtokg.dto.request.ProfileRequestDTO;
import com.company.avtokg.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = "Profile")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    @ApiOperation(value = "Create ", notes = "Method: Create Profile")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid ProfileRequestDTO requestDTO) {
        log.info("Create: {}", requestDTO);
        return ResponseEntity.ok(profileService.create(requestDTO));
    }

    @ApiOperation(value = "Get by id", notes = "Method: by Profile id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        log.info("Get by id: {}", id);
        return ResponseEntity.ok(profileService.getById(id));
    }


    @ApiOperation(value = "Delete by id", notes = "Method: Delete by Profile id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("Delete by id: {}", id);
        return ResponseEntity.ok(profileService.delete(id));
    }

    @ApiOperation(value = "Change Status", notes = "Method: by Profile id")
    @PutMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") String id,
                                          @RequestBody ProfileChangeStatusRequestDTO requestDTO) {
        log.info("Change status by id: {},{}", requestDTO, id);
        return ResponseEntity.ok(profileService.changeStatus(id, requestDTO));
    }

    @ApiOperation(value = "update detail", notes = "Method used for update detail")
    @PutMapping("/update-detail/{id}")
    public ResponseEntity<?> updateDerail(@PathVariable String id, ProfileRequestDTO dto) {
        log.info("update detail: {}{}", id, dto);
        return ResponseEntity.ok(profileService.updateDetail(id, dto));
    }


    @ApiOperation(value = "update password", notes = "Method used for update password")
    @PutMapping("/{prePassword}/update-password/{newPassword}/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable("prePassword") String prePassword,
                                            @PathVariable("newPassword") String newPassword,
                                            @PathVariable String id) {
        log.info("update password: {}{}{}{}", id, prePassword, newPassword, ProfileController.class);
        return ResponseEntity.ok(profileService.updatePassword(prePassword, newPassword, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "get pagination by id", notes = "Method used for get pagination by profileId")
    @GetMapping("/pagination{profileId}")
    public ResponseEntity<?> getPaginationByProfileId(@PathVariable("profileId") String profileId,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "3") int size) {
        log.info("id not found: {}{}", profileId, ProfileController.class);
        return ResponseEntity.ok(profileService.getPagination(page, size, profileId));
    }




}
