package br.com.stoom.store.brand.dto;

import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.common.enums.EStatus;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {

    private Long id;
    private String name;
    private EStatus status;

    public static BrandResponse of(Brand brand) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(brand, BrandResponse.class);
    }
}
