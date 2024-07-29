package br.com.stoom.store.helper;

import br.com.stoom.store.brand.dto.BrandRequest;
import br.com.stoom.store.brand.dto.BrandResponse;
import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.common.enums.EStatus;

import java.util.List;
import java.util.Optional;

public class BrandHelper {

    public static Brand oneBrand() {
        return Brand.builder()
                .id(1L)
                .name("Brand Test")
                .status(EStatus.A)
                .build();
    }

    public static BrandRequest oneBrandRequest() {
        return BrandRequest.builder()
                .name("Brand Request Test")
                .build();
    }

    public static BrandResponse oneBrandResponse() {
        return BrandResponse.builder()
                .id(1L)
                .name("Brand Response Test")
                .status(EStatus.A)
                .build();
    }

    public static Optional<Brand> oneBrandOptional() {
        return Optional.of(Brand.builder()
                .id(1L)
                .name("Brand Optional Test")
                .status(EStatus.A)
                .build());
    }

    public static List<Brand> oneBrandList() {
        return List.of(oneBrand());
    }
}
