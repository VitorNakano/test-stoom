package br.com.stoom.store.product.model;

import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static br.com.stoom.store.helper.ProductHelper.oneProductRequest;
import static br.com.stoom.store.helper.ProductHelper.oneProductResponse;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void converFrom_mustReturnNewProduct_whenCalled() {
        assertThat(Product.converFrom(oneProductRequest(), new Product()))
            .extracting("name", "sku", "description", "price", "status")
            .containsExactly("Product Test", "123456", "Test", BigDecimal.ONE, EStatus.A);
    }

    @Test
    void convertFromResponse_mustReturnNewProduct_whenCalled() {
        assertThat(Product.converFromResponse(oneProductResponse()))
            .extracting("id", "name", "sku", "description",
                    "brand.id", "brand.name", "brand.status",
                    "category.id", "category.name", "category.status",
                    "price", "status")
            .containsExactly( 1L, "Product Test", "123456", "Test",
                    1L, "Brand Test", EStatus.A,
                    1L, "Category Test", EStatus.A,
                    BigDecimal.ONE, EStatus.A);
    }
}
