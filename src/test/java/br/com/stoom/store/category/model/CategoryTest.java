package br.com.stoom.store.category.model;

import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;

import static br.com.stoom.store.helper.CategoryHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    void convertFrom_mustReturnNewCategory_whenCalled() {
        assertThat(Category.convertFrom(oneCategoryRequest()))
                .extracting("name", "status")
                .containsExactly("Category Request Test", EStatus.A);
    }

    @Test
    void convertFromOptional_mustReturnNewCategory_whenCalled() {
        assertThat(Category.convertFromOptional(oneCategoryOptional()))
                .extracting("id", "name", "status")
                .containsExactly(1L, "Category Optional Test", EStatus.A);
    }

    @Test
    void convertFromResponse_mustReturnNewCategory_whenCalled() {
        assertThat(Category.convertFromResponse(oneCategoryResponse()))
                .extracting("id", "name", "status")
                .containsExactly(1L, "Category Response Test", EStatus.A);
    }
}
