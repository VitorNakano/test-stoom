package br.com.stoom.store.brand.model;

import br.com.stoom.store.brand.dto.BrandRequest;
import br.com.stoom.store.brand.dto.BrandResponse;
import br.com.stoom.store.common.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Data
@Entity
@Builder
@Table(name = "BRAND")
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    private static final ModelMapper mapper = new ModelMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE_BRAND")
    @SequenceGenerator(name = "SEQ_BRAND", sequenceName = "SEQ_BRAND", allocationSize = 1)
    private Long id;

    @Column(name = "BRAND_NAME", nullable = false)
    private String name;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    public static Brand convertFrom(BrandRequest request) {
        Brand brand = mapper.map(request, Brand.class);
        brand.setStatus(EStatus.A);
        return brand;
    }

    public static Brand convertFromOptional(Optional<Brand> optional) {
        return mapper.map(optional, Brand.class);
    }

    public static Brand convertFromResponse(BrandResponse response) {
        return mapper.map(response, Brand.class);
    }
}
