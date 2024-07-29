package br.com.stoom.store.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank
    private String sku;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long brandId;
    @Min(0)
    @NotNull
    private BigDecimal price;
}
