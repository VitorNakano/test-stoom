package br.com.stoom.store.product.dto;

import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.product.model.Product;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private Category category;
    private Brand brand;
    private BigDecimal price;
    private EStatus status;

    public static ProductResponse of(Product product) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(product, ProductResponse.class);
    }
}
