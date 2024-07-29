package br.com.stoom.store.category.controller;

import br.com.stoom.store.category.business.CategoryBO;
import br.com.stoom.store.category.dto.CategoryRequest;
import br.com.stoom.store.category.dto.CategoryResponse;
import br.com.stoom.store.common.enums.EStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryBO service;

    @PostMapping("/")
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        return Optional.of(service.save(request))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return Optional.ofNullable(service.findAll())
                .filter(categories -> !categories.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/id")
    public ResponseEntity<CategoryResponse> getById(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.findById(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{status}/status")
    public ResponseEntity<List<CategoryResponse>> getByStatus(@PathVariable("status") EStatus status) {
        return Optional.ofNullable(service.findByStatus(status))
                .filter(brands -> !brands.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<CategoryResponse> activate(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.activate(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/inactivate")
    public ResponseEntity<CategoryResponse> inactivate(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.inactivate(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable("id") Long id,
                                                         @RequestBody CategoryRequest request) {
        CategoryResponse response = service.editCategory(id, request);
        return ResponseEntity.ok().body(response);
    }
}
