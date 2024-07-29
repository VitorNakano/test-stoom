package br.com.stoom.store.category.business;

import br.com.stoom.store.category.business.interfaces.ICategoryBO;
import br.com.stoom.store.category.dto.CategoryRequest;
import br.com.stoom.store.category.dto.CategoryResponse;
import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.category.repository.CategoryRepository;
import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.common.exceptions.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.stoom.store.common.validators.Validator.validateStatus;

@Service
public class CategoryBO implements ICategoryBO {

    private static final String NOT_FOUND_BY_ID = "Category not found by ID: ";
    @Autowired
    private CategoryRepository repository;

    @Override
    @Transactional
    public CategoryResponse save(CategoryRequest request) {
        Category response = repository.save(Category.convertFrom(request));
        return CategoryResponse.of(response);
    }

    @Override
    public List<CategoryResponse> findAll() {
        return repository.findAll().stream()
            .map(CategoryResponse::of)
            .toList();
    }

    @Override
    public CategoryResponse findById(Long id) {
        return repository.findById(id)
            .map(CategoryResponse::of)
            .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    public List<CategoryResponse> findByStatus(EStatus status) {
        if (!validateStatus(status)) {
            throw new ValidationException("Invalid status. Allowed values are A or I");
        }
        return repository.findByStatus(status).stream()
            .map(CategoryResponse::of)
            .toList();
    }

    @Override
    @Transactional
    public CategoryResponse activate(Long id) {
        return Optional.of(repository.save(changeCategoryStatus(id, EStatus.A)))
            .map(CategoryResponse::of)
            .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    @Transactional
    public CategoryResponse inactivate(Long id) {
        return Optional.of(repository.save(changeCategoryStatus(id, EStatus.I)))
            .map(CategoryResponse::of)
            .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    @Transactional
    public CategoryResponse editCategory(Long id, CategoryRequest request) {
        Category category = Category.convertFromResponse(findById(id));
        mapCategory(request, category);
        return CategoryResponse.of(repository.save(category));
    }

    private Category changeCategoryStatus(Long id, EStatus status) {
        Category category = Category.convertFromOptional(repository.findById(id));
        category.setStatus(status);
        return category;
    }

    private void mapCategory(CategoryRequest request, Category category) {
        ModelMapper mapper = new ModelMapper();
        mapper.map(request, category);
    }
}
