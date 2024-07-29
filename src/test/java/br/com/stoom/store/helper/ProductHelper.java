package br.com.stoom.store.helper;

import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.product.dto.ProductRequest;
import br.com.stoom.store.product.dto.ProductResponse;
import br.com.stoom.store.product.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static br.com.stoom.store.helper.BrandHelper.oneBrand;
import static br.com.stoom.store.helper.CategoryHelper.oneCategory;

public class ProductHelper {

    public static Product oneProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product Test");
        product.setSku("123456");
        product.setDescription("Test");
        product.setBrand(oneBrand());
        product.setCategory(oneCategory());
        product.setPrice(BigDecimal.ONE);
        product.setStatus(EStatus.A);
        return product;
    }
    public static ProductRequest oneProductRequest() {
        return ProductRequest.builder()
                .name("Product Test")
                .sku("123456")
                .description("Test")
                .brandId(1L)
                .categoryId(1L)
                .price(BigDecimal.ONE)
                .build();
    }

    public static ProductResponse oneProductResponse() {
        return ProductResponse.builder()
                .id(1L)
                .name("Product Test")
                .sku("123456")
                .description("Test")
                .brand(oneBrand())
                .category(oneCategory())
                .price(BigDecimal.ONE)
                .status(EStatus.A)
                .build();
    }

    public static List<Product> oneProductList() {
        return List.of(oneProduct());
    }
}
