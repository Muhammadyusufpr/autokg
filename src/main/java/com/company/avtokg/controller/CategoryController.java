package com.company.avtokg.controller;

import com.company.avtokg.dto.CategoryDTO;
import com.company.avtokg.enums.ProfileRole;
import com.company.avtokg.service.CategoryService;
import com.company.avtokg.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Api(tags = "Category")
public class CategoryController {

    private final CategoryService categoryService;


    //1. Create Category (ADMIN)
    @ApiOperation(value = "Create", notes = "Method used for create category",
            authorizations = @Authorization(value = "JWT Token"))
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody CategoryDTO dto) {
        log.info("Category {}", dto);
        return ResponseEntity.ok(categoryService.create(dto));
    }


    // 2. Update Category (ADMIN)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Update", notes = "Method used for update category",
            authorizations = @Authorization(value = "JWT Token"))
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id,
                                    @RequestBody CategoryDTO dto) {
        log.info("Category {}", dto);
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    //3. Delete Category (ADMIN)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "delete", notes = "Method used for delete category by Id")
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("Category {}", id);
        return ResponseEntity.ok(categoryService.delete(id));
    }

    //  5. Category List
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "list", notes = "Method used for pagination list")
    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(categoryService.paginationList(page, size));
    }
}
