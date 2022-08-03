package com.company.avtokg.controller;


import com.company.avtokg.dto.RegionDTO;
import com.company.avtokg.enums.LangEnum;
import com.company.avtokg.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/region")
@Api(tags = "Region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;


    @ApiOperation(value = "create region", notes = "Method used for create region")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable("id") String pid,
                                    @RequestBody RegionDTO dto) {
        log.info("Region {}", dto);
        return ResponseEntity.ok(regionService.create(dto, pid));
    }

    @ApiOperation(value = "get list", notes = "Method used for get region List for admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adm")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(regionService.list());
    }

    @ApiOperation(value = "get list by lang", notes = "Method used for get region list by lang")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/public/{lang}")
    public ResponseEntity<?> list(@PathVariable("lang") LangEnum lang) {
        return ResponseEntity.ok(regionService.list(lang));
    }

    @ApiOperation(value = "update", notes = "Method used for update region")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody RegionDTO dto) {
        return ResponseEntity.ok(regionService.update(id, dto));
    }

    @ApiOperation(value = "delete", notes = "Method used for delete region")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(regionService.delete(id));
    }
}
