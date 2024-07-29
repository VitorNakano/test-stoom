package br.com.stoom.store.brand.controller;

import br.com.stoom.store.brand.business.BrandBO;
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

import static br.com.stoom.store.helper.BrandHelper.*;
import static br.com.stoom.store.helper.TestHelper.convertObjectToJsonBytes;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandController.class)
@ExtendWith(SpringExtension.class)
class BrandControllerTest {

    private static final String URL = "/api/brands";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BrandBO service;

    @Test
    @SneakyThrows
    @WithMockUser
    void save_shouldReturnOk_whenCalled() {
        doReturn(oneBrandResponse())
            .when(service).save(any());

        mockMvc.perform(post(URL.concat("/"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(oneBrandRequest()))
                .with(csrf()))
            .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getAll_shouldReturnAllBrands_whenCalled() {
        doReturn(oneBrandList())
            .when(service).findAll();

        mockMvc.perform(get(URL.concat("/"))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].name", is("Brand Test")))
            .andExpect(jsonPath("$[0].status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getById_shouldReturnBrand_whenCalled() {
        doReturn(oneBrandResponse())
                .when(service).findById(1L);

        mockMvc.perform(get(URL.concat("/{id}/id"), 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Brand Response Test")))
                .andExpect(jsonPath("$.status", is("A")));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void getByStatus_shouldReturnBrand_whenCalled() {
        doReturn(oneBrandList())
                .when(service).findByStatus(EStatus.A);

        mockMvc.perform(get(URL.concat("/{status}/status"), EStatus.A)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void activate_shouldActivateBrand_whenCalled() {
        doReturn(oneBrandResponse())
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
    void inactivate_shouldInactivateBrand_whenCalled() {
        doReturn(oneBrandResponse())
                .when(service).inactivate(1L);

        mockMvc.perform(put(URL.concat("/{id}/inactivate"), 1L)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void editBrand_shouldEditBrand_whenCalled() {
        doReturn(oneBrandResponse())
                .when(service).editBrand(1L, oneBrandRequest());

        mockMvc.perform(put(URL.concat("/{id}/edit"), 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(oneBrandRequest()))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
