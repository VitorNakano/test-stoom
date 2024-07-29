package br.com.stoom.store.brand.controller;

import br.com.stoom.store.brand.business.BrandBO;
import br.com.stoom.store.brand.dto.BrandRequest;
import br.com.stoom.store.brand.dto.BrandResponse;
import br.com.stoom.store.common.enums.EStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/brands")
public class BrandController {

    @Autowired
    private BrandBO service;

    @PostMapping("/")
    public ResponseEntity<BrandResponse> save(@Valid @RequestBody BrandRequest request) {
        BrandResponse response = service.save(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<BrandResponse>> getAll() {
        return Optional.ofNullable(service.findAll())
                .filter(brands -> !brands.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/id")
    public ResponseEntity<BrandResponse> getById(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.findById(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{status}/status")
    public ResponseEntity<List<BrandResponse>> getByStatus(@PathVariable("status") EStatus status) {
        return Optional.ofNullable(service.findByStatus(status))
                .filter(brands -> !brands.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<BrandResponse> activate(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.activate(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/inactivate")
    public ResponseEntity<BrandResponse> inactivate(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.inactivate(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<BrandResponse> editBrand(@PathVariable Long id,
                                                   @RequestBody BrandRequest request) {
        BrandResponse response = service.editBrand(id, request);
        return ResponseEntity.ok().body(response);
    }
}
