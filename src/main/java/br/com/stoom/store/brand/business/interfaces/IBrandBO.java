package br.com.stoom.store.brand.business.interfaces;

import br.com.stoom.store.brand.dto.BrandRequest;
import br.com.stoom.store.brand.dto.BrandResponse;
import br.com.stoom.store.common.enums.EStatus;

import java.util.List;

public interface IBrandBO {

    BrandResponse save(BrandRequest request);
    List<BrandResponse> findAll();
    BrandResponse findById(Long id);
    List<BrandResponse> findByStatus(EStatus status);
    BrandResponse activate(Long id);
    BrandResponse inactivate(Long id);
    BrandResponse editBrand(Long id, BrandRequest request);
}
