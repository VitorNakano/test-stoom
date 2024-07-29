package br.com.stoom.store.product.controller;

import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.product.business.ProductBO;
import br.com.stoom.store.product.dto.ProductRequest;
import br.com.stoom.store.product.dto.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductBO service;

    @PostMapping("/")
    @Transactional
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = service.save(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> findAll() {
        return Optional.ofNullable(service.findAll())
                .filter(products -> !products.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get-activated")
    public ResponseEntity<List<ProductResponse>> findAllActivated() {
        return Optional.ofNullable(service.findAllActivatedProducts())
                .filter(products -> !products.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{brandId}/by-brand")
    public ResponseEntity<List<ProductResponse>> getAllByBrandId(@PathVariable("brandId") Long brandId) {
        return Optional.of(service.findAllByBrandId(brandId))
                .filter(products -> !products.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{categoryId}/by-category")
    public ResponseEntity<List<ProductResponse>> getAllByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return Optional.of(service.findAllByCategoryId(categoryId))
                .filter(products -> !products.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}/id")
    public ResponseEntity<ProductResponse> getById(@PathVariable("id") Long id) {
        return Optional.of(service.findById(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{status}/status")
    public ResponseEntity<List<ProductResponse>> getByStatus(@PathVariable("status") EStatus status) {
        return Optional.of(service.findAllByStatus(status))
                .filter(products -> !products.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ProductResponse> activate(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.activate(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/inactivate")
    public ResponseEntity<ProductResponse> inactivate(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.inactivate(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable("id") Long id,
                                                       @RequestBody ProductRequest request) {
        ProductResponse response = service.editProduct(id, request);
        return ResponseEntity.ok().body(response);
    }
}
