package br.com.stoom.store.product.dto;

import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static br.com.stoom.store.helper.ProductHelper.oneProduct;

class ProductResponseTest {

    @Test
    void of_mustReturnNewProductResponse_whenCalled() {
        assertThat(ProductResponse.of(oneProduct()))
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
