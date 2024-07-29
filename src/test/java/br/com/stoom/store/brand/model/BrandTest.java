package br.com.stoom.store.brand.model;

import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;

import static br.com.stoom.store.helper.BrandHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

class BrandTest {

    @Test
    void convertFrom_mustReturnNewBrand_whenCalled() {
        assertThat(Brand.convertFrom(oneBrandRequest()))
                .extracting("name", "status")
                .containsExactly("Brand Request Test", EStatus.A);
    }

    @Test
    void convertFromOptional_mustReturnNewBrand_whenCalled() {
        assertThat(Brand.convertFromOptional(oneBrandOptional()))
                .extracting("id", "name", "status")
                .containsExactly(1L, "Brand Optional Test", EStatus.A);
    }

    @Test
    void convertFromResponse_mustReturnNewBrand_whenCalled() {
        assertThat(Brand.convertFromResponse(oneBrandResponse()))
                .extracting("id", "name", "status")
                .containsExactly(1L, "Brand Response Test", EStatus.A);
    }
}
