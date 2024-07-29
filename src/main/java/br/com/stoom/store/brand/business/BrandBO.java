package br.com.stoom.store.brand.business;

import br.com.stoom.store.brand.business.interfaces.IBrandBO;
import br.com.stoom.store.brand.dto.BrandRequest;
import br.com.stoom.store.brand.dto.BrandResponse;
import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.brand.repository.BrandRepository;
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
public class BrandBO implements IBrandBO {

    private static final String NOT_FOUND_BY_ID = "Brand not found by ID: ";
    @Autowired
    private BrandRepository repository;

    @Override
    @Transactional
    public BrandResponse save(BrandRequest request) {
        Brand response = repository.save(Brand.convertFrom(request));
        return BrandResponse.of(response);
    }

    @Override
    public List<BrandResponse> findAll() {
        return repository.findAll().stream()
            .map(BrandResponse::of)
            .toList();
    }

    @Override
    public BrandResponse findById(Long id) {
        return repository.findById(id)
            .map(BrandResponse::of)
            .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    public List<BrandResponse> findByStatus(EStatus status) {
        if (!validateStatus(status)) {
            throw new ValidationException("Invalid status. Allowed values are A or I");
        }
        return repository.findByStatus(status).stream()
            .map(BrandResponse::of)
            .toList();
    }

    @Override
    @Transactional
    public BrandResponse activate(Long id) {
        Brand brand = changeBrandStatus(id, EStatus.A);
        return Optional.of(repository.save(brand))
            .map(BrandResponse::of)
            .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    @Transactional
    public BrandResponse inactivate(Long id) {
        Brand brand = changeBrandStatus(id, EStatus.I);
        return Optional.of(repository.save(brand))
            .map(BrandResponse::of)
            .orElseThrow(() -> new ValidationException(NOT_FOUND_BY_ID + id));
    }

    @Override
    @Transactional
    public BrandResponse editBrand(Long id, BrandRequest request) {
        Brand brand = Brand.convertFromResponse(findById(id));
        mapBrand(request, brand);
        return BrandResponse.of(repository.save(brand));
    }

    private Brand changeBrandStatus(Long id, EStatus status) {
        Brand newBrand = Brand.convertFromOptional(repository.findById(id));
        newBrand.setStatus(status);
        return newBrand;
    }

    private void mapBrand(BrandRequest request, Brand brand) {
        ModelMapper mapper = new ModelMapper();
        mapper.map(request, brand);
    }
}
