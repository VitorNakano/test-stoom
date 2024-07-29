package br.com.stoom.store.category.business.interfaces;

import br.com.stoom.store.category.dto.CategoryRequest;
import br.com.stoom.store.category.dto.CategoryResponse;
import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.common.enums.EStatus;

import java.util.List;

public interface ICategoryBO {

    CategoryResponse save(CategoryRequest request);
    List<CategoryResponse> findAll();
    CategoryResponse findById(Long id);
    List<CategoryResponse> findByStatus(EStatus status);
    CategoryResponse activate(Long id);
    CategoryResponse inactivate(Long id);
    CategoryResponse editCategory(Long id, CategoryRequest request);
}
