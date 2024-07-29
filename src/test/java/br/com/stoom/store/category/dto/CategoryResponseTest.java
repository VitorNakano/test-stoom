package br.com.stoom.store.category.dto;

import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;

import static br.com.stoom.store.helper.CategoryHelper.oneCategory;
import static org.assertj.core.api.Assertions.assertThat;

class CategoryResponseTest {

    @Test
    void of_mustReturnNewResponse_whenCalled() {
        assertThat(CategoryResponse.of(oneCategory()))
                .extracting("id", "name", "status")
                .containsExactly(1L, "Category Test", EStatus.A);
    }
}
