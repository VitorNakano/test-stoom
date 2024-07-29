package br.com.stoom.store.product.controller;

import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.product.business.ProductBO;
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

import static br.com.stoom.store.helper.ProductHelper.*;
import static br.com.stoom.store.helper.TestHelper.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith(SpringExtension.class)
class ProductControllerTest {

    private static final String URL = "/api/products";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductBO service;

    @Test
    @SneakyThrows
    @WithMockUser
    void save_shouldReturnOk_whenCalled() {
        doReturn(oneProductResponse())
                .when(service).save(any());

        mockMvc.perform(post(URL.concat("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(oneProductRequest()))
                .with(csrf()))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void findAll_shoulReturnAllProducts_whenCalled() {
        doReturn(oneProductList())
                .when(service).findAll();

        mockMvc.perform(get(URL.concat("/"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product Test")))
                .andExpect(jsonPath("$[0].sku", is("123456")))
                .andExpect(jsonPath("$[0].description", is("Test")))
                .andExpect(jsonPath("$[0].brand.id", is(1)))
                .andExpect(jsonPath("$[0].brand.name", is("Brand Test")))
                .andExpect(jsonPath("$[0].brand.status", is("A")))
                .andExpect(jsonPath("$[0].category.id", is(1)))
                .andExpect(jsonPath("$[0].category.name", is("Category Test")))
                .andExpect(jsonPath("$[0].category.status", is("A")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void findAllActivated_shoulReturnAllProducts_whenCalled() {
        doReturn(oneProductList())
                .when(service).findAllActivatedProducts();

        mockMvc.perform(get(URL.concat("/get-activated"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product Test")))
                .andExpect(jsonPath("$[0].sku", is("123456")))
                .andExpect(jsonPath("$[0].description", is("Test")))
                .andExpect(jsonPath("$[0].brand.id", is(1)))
                .andExpect(jsonPath("$[0].brand.name", is("Brand Test")))
                .andExpect(jsonPath("$[0].brand.status", is("A")))
                .andExpect(jsonPath("$[0].category.id", is(1)))
                .andExpect(jsonPath("$[0].category.name", is("Category Test")))
                .andExpect(jsonPath("$[0].category.status", is("A")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getAllByBrandId_shouldReturnAllProductsWithBrandId_whenCalled() {
        doReturn(oneProductList())
                .when(service).findAllByBrandId(1L);

        mockMvc.perform(get(URL.concat("/{brandId}/by-brand"), 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product Test")))
                .andExpect(jsonPath("$[0].sku", is("123456")))
                .andExpect(jsonPath("$[0].description", is("Test")))
                .andExpect(jsonPath("$[0].brand.id", is(1)))
                .andExpect(jsonPath("$[0].brand.name", is("Brand Test")))
                .andExpect(jsonPath("$[0].brand.status", is("A")))
                .andExpect(jsonPath("$[0].category.id", is(1)))
                .andExpect(jsonPath("$[0].category.name", is("Category Test")))
                .andExpect(jsonPath("$[0].category.status", is("A")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getAllByCategoryId_shouldReturnAllProductsWithBrandId_whenCalled() {
        doReturn(oneProductList())
                .when(service).findAllByCategoryId(1L);

        mockMvc.perform(get(URL.concat("/{categoryId}/by-category"), 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product Test")))
                .andExpect(jsonPath("$[0].sku", is("123456")))
                .andExpect(jsonPath("$[0].description", is("Test")))
                .andExpect(jsonPath("$[0].brand.id", is(1)))
                .andExpect(jsonPath("$[0].brand.name", is("Brand Test")))
                .andExpect(jsonPath("$[0].brand.status", is("A")))
                .andExpect(jsonPath("$[0].category.id", is(1)))
                .andExpect(jsonPath("$[0].category.name", is("Category Test")))
                .andExpect(jsonPath("$[0].category.status", is("A")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getById_shouldReturnProduct_whenCalled() {
        doReturn(oneProductResponse())
                .when(service).findById(1L);

        mockMvc.perform(get(URL.concat("/{id}/id"), 1L)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product Test")))
                .andExpect(jsonPath("$.sku", is("123456")))
                .andExpect(jsonPath("$.description", is("Test")))
                .andExpect(jsonPath("$.brand.id", is(1)))
                .andExpect(jsonPath("$.brand.name", is("Brand Test")))
                .andExpect(jsonPath("$.brand.status", is("A")))
                .andExpect(jsonPath("$.category.id", is(1)))
                .andExpect(jsonPath("$.category.name", is("Category Test")))
                .andExpect(jsonPath("$.category.status", is("A")))
                .andExpect(jsonPath("$.price", is(1)))
                .andExpect(jsonPath("$.status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getByStatus_shouldReturnAllProductsByStatus_whenCalled() {
        doReturn(oneProductList())
                .when(service).findAllByStatus(EStatus.A);

        mockMvc.perform(get(URL.concat("/{status}/status"), EStatus.A)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product Test")))
                .andExpect(jsonPath("$[0].sku", is("123456")))
                .andExpect(jsonPath("$[0].description", is("Test")))
                .andExpect(jsonPath("$[0].brand.id", is(1)))
                .andExpect(jsonPath("$[0].brand.name", is("Brand Test")))
                .andExpect(jsonPath("$[0].brand.status", is("A")))
                .andExpect(jsonPath("$[0].category.id", is(1)))
                .andExpect(jsonPath("$[0].category.name", is("Category Test")))
                .andExpect(jsonPath("$[0].category.status", is("A")))
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[0].status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void activate_shouldActivateProduct_whenCalled() {
        doReturn(oneProductResponse())
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
    void inactivate_shouldActivateProduct_whenCalled() {
        doReturn(oneProductResponse())
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
    void editProduct_shouldActivateProduct_whenCalled() {
        doReturn(oneProductResponse())
                .when(service).editProduct(1L, oneProductRequest());

        mockMvc.perform(put(URL.concat("/{id}/edit"), 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(oneProductRequest()))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
