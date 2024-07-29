package br.com.stoom.store.category.business;


import br.com.stoom.store.category.dto.CategoryResponse;
import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.category.repository.CategoryRepository;
import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.stoom.store.helper.CategoryHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryBOTest {

    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private CategoryBO service;

    @Test
    void save_shouldSaveCategory_whenCalled() {
        doReturn(oneCategory())
                .when(repository).save(any());

        CategoryResponse response = service.save(oneCategoryRequest());

        assertThat(response).isNotNull();

        verify(repository).save(any());
    }

    @Test
    void findAll_shouldReturnAllCategories_whenCalled() {
        doReturn(oneCategoryList())
                .when(repository).findAll();

        List<CategoryResponse> list = service.findAll();
        assertThat(list).hasSize(1);

        verify(repository).findAll();
    }

    @Test
    void findById_shouldReturnCategory_whenCalled() {
        doReturn(oneCategoryOptional())
                .when(repository).findById(1L);

        CategoryResponse response = service.findById(1L);

        assertThat(response).isNotNull();

        verify(repository).findById(1L);
    }

    @Test
    void findByStatus_shouldReturnAllCategories_whenCalled() {
        doReturn(oneCategoryList())
                .when(repository).findByStatus(EStatus.A);

        List<CategoryResponse> list = service.findByStatus(EStatus.A);
        assertThat(list).hasSize(1);

        verify(repository).findByStatus(EStatus.A);
    }

    @Test
    void activate_shouldActivateCategory_whenCalled() {
        doReturn(oneCategoryOptional()).when(repository).findById(1L);
        doReturn(oneCategory()).when(repository).save(any());

        CategoryResponse response = service.activate(1L);

        assertThat(response.getStatus()).isEqualTo(EStatus.A);

        verify(repository).save(any());
    }

    @Test
    void inactivate_shouldActivateCategory_whenCalled() {
        Category inactive = oneCategory();
        inactive.setStatus(EStatus.I);

        doReturn(oneCategoryOptional()).when(repository).findById(1L);
        doReturn(inactive).when(repository).save(any());

        CategoryResponse response = service.inactivate(1L);

        assertThat(response.getStatus()).isEqualTo(EStatus.I);

        verify(repository).save(any());
    }

    @Test
    void editCategory_shouldEditCategory_whenCalled() {
        doReturn(oneCategoryOptional()).when(repository).findById(1L);
        doReturn(oneCategory()).when(repository).save(any());

        CategoryResponse response = service.editCategory(1L, oneCategoryRequest());

        assertThat(response)
                .extracting("id", "name", "status")
                .containsExactly(1L, "Category Test", EStatus.A);

        verify(repository).save(any());
    }
}
