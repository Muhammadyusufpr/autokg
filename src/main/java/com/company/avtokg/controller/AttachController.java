package com.company.avtokg.controller;

import com.company.avtokg.dto.AttachDTO;
import com.company.avtokg.service.AttachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/attach")
@Api(tags = "Attach")
@RequiredArgsConstructor
public class AttachController {
    private final AttachService attachService;


    @ApiOperation(value = "Upload", notes = "Method used for upload MultipartFile")
    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> create(@RequestParam("file") MultipartFile file) {
        log.info("Upload MultipartFile {}",file);
        return ResponseEntity.ok(attachService.upload(file));
    }

    @ApiOperation(value = "Open general", notes = "Method used for open files")
    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.open_general(fileName);
    }

    @ApiOperation(value = "Download", notes = "Method used for download MultipartFile")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> download(@PathVariable("fileName") String fileName) {
        return attachService.download(fileName);
    }

    @ApiOperation(value = "List", notes = "Method used for all attach list for Admin")
    @GetMapping("/adm/list")
    public ResponseEntity<List<AttachDTO>> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "3") int size,
                                     HttpServletRequest request) {
        return ResponseEntity.ok(attachService.paginationList(page, size));
    }

    @ApiOperation(value = "Delete", notes = "Method used for delete MultipartFile for Admin")
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String key, HttpServletRequest request) {
        return ResponseEntity.ok(attachService.delete(key));
    }
}
