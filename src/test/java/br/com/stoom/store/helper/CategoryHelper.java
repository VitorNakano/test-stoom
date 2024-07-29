package br.com.stoom.store.helper;

import br.com.stoom.store.category.dto.CategoryRequest;
import br.com.stoom.store.category.dto.CategoryResponse;
import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.common.enums.EStatus;

import java.util.List;
import java.util.Optional;

public class CategoryHelper {

    public static Category oneCategory() {
        return Category.builder()
                .id(1L)
                .name("Category Test")
                .status(EStatus.A)
                .build();
    }

    public static CategoryRequest oneCategoryRequest() {
        return CategoryRequest.builder()
                .name("Category Request Test")
                .build();
    }

    public static Optional<Category> oneCategoryOptional() {
        return Optional.of(Category.builder()
                .id(1L)
                .name("Category Optional Test")
                .status(EStatus.A).build());
    }

    public static CategoryResponse oneCategoryResponse() {
        return CategoryResponse.builder()
                .id(1L)
                .name("Category Response Test")
                .status(EStatus.A)
                .build();
    }

    public static List<Category> oneCategoryList() {
        return List.of(oneCategory());
    }
}
