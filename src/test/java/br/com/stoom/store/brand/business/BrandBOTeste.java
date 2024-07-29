package br.com.stoom.store.brand.business;

import br.com.stoom.store.brand.dto.BrandResponse;
import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.brand.repository.BrandRepository;
import br.com.stoom.store.common.enums.EStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.stoom.store.helper.BrandHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BrandBOTeste {

    @Mock
    private BrandRepository repository;
    @InjectMocks
    private BrandBO service;

    @Test
    void save_shouldSaveBrand_whenCalled() {
        doReturn(oneBrand())
                .when(repository).save(any());

        BrandResponse response = service.save(oneBrandRequest());

        assertThat(response).isNotNull();

        verify(repository).save(any());
    }

    @Test
    void findAll_shouldReturnAllBrands_whenCalled() {
        doReturn(oneBrandList())
                .when(repository).findAll();

        List<BrandResponse> list = service.findAll();
        assertThat(list).hasSize(1);

        verify(repository).findAll();
    }

    @Test
    void findById_shouldReturnBrand_whenCalled() {
        doReturn(oneBrandOptional())
                .when(repository).findById(1L);

        BrandResponse response = service.findById(1L);

        assertThat(response).isNotNull();

        verify(repository).findById(1L);
    }

    @Test
    void findByStatus_shouldReturnAllBrands_whenCalled() {
        doReturn(oneBrandList())
                .when(repository).findByStatus(EStatus.A);

        List<BrandResponse> list = service.findByStatus(EStatus.A);
        assertThat(list).hasSize(1);

        verify(repository).findByStatus(EStatus.A);
    }

    @Test
    void activate_shouldActivateBrand_whenCalled() {
        doReturn(oneBrandOptional()).when(repository).findById(1L);
        doReturn(oneBrand()).when(repository).save(any());

        BrandResponse response = service.activate(1L);

        assertThat(response.getStatus()).isEqualTo(EStatus.A);

        verify(repository).save(any());
    }

    @Test
    void inactivate_shouldActivateBrand_whenCalled() {
        Brand inactive = oneBrand();
        inactive.setStatus(EStatus.I);

        doReturn(oneBrandOptional()).when(repository).findById(1L);
        doReturn(inactive).when(repository).save(any());

        BrandResponse response = service.inactivate(1L);

        assertThat(response.getStatus()).isEqualTo(EStatus.I);

        verify(repository).save(any());
    }

    @Test
    void editBrand_shouldEditBrand_whenCalled() {
        doReturn(oneBrandOptional()).when(repository).findById(1L);
        doReturn(oneBrand()).when(repository).save(any());

        BrandResponse response = service.editBrand(1L, oneBrandRequest());

        assertThat(response)
                .extracting("id", "name", "status")
                .containsExactly(1L, "Brand Test", EStatus.A);

        verify(repository).save(any());
    }
}
