package br.com.stoom.store.category.controller;

import br.com.stoom.store.category.business.CategoryBO;
import br.com.stoom.store.common.enums.EStatus;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.stoom.store.helper.CategoryHelper.*;
import static br.com.stoom.store.helper.TestHelper.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {

    private static final String URL = "/api/categories";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryBO service;

    @Test
    @SneakyThrows
    @WithMockUser
    void save_shouldReturnOk_whenCalled() {
        doReturn(oneCategoryResponse())
            .when(service).save(any());

        mockMvc.perform(post(URL.concat("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(oneCategoryRequest()))
                .with(csrf()))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getAll_shouldReturnAllCategories_whenCalled() {
        doReturn(oneCategoryList())
                .when(service).findAll();

        mockMvc.perform(get(URL.concat("/"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Category Test")))
                .andExpect(jsonPath("$[0].status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getById_shouldReturnCategory_whenCalled() {
        doReturn(oneCategoryResponse())
                .when(service).findById(1L);

        mockMvc.perform(get(URL.concat("/{id}/id"), 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Category Response Test")))
                .andExpect(jsonPath("$.status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getByStatus_shouldReturnCategory_whenCalled() {
        doReturn(oneCategoryList())
                .when(service).findByStatus(EStatus.A);

        mockMvc.perform(get(URL.concat("/{status}/status"), EStatus.A)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void activate_shouldActivateCategory_whenCalled() {
        doReturn(oneCategoryResponse())
                .when(service).activate(1L);

        mockMvc.perform(put(URL.concat("/{id}/activate"), 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void inactivate_shouldInactivateCategory_whenCalled() {
        doReturn(oneCategoryResponse())
                .when(service).inactivate(1L);

        mockMvc.perform(put(URL.concat("/{id}/inactivate"), 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void editCategory_shouldEditCategory_whenCalled() {
        doReturn(oneCategoryResponse())
                .when(service).editCategory(1L, oneCategoryRequest());

        mockMvc.perform(put(URL.concat("/{id}/edit"), 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(oneCategoryRequest()))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
