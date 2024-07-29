package br.com.stoom.store.brand.dto;


import org.junit.jupiter.api.Test;

import static br.com.stoom.store.helper.BrandHelper.oneBrand;
import static org.assertj.core.api.Assertions.assertThat;

class BrandResponseTest {

    @Test
    void of_mustReturnNewResponse_whenCalled() {
        assertThat(BrandResponse.of(oneBrand()))
                .extracting("name")
                .isEqualTo("Brand Test");
    }
}
