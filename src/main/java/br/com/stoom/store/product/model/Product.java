package br.com.stoom.store.product.model;

import br.com.stoom.store.brand.model.Brand;
import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.common.enums.EStatus;
import br.com.stoom.store.product.dto.ProductRequest;
import br.com.stoom.store.product.dto.ProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PRODUCT")
    @SequenceGenerator(name = "SEQUENCE_PRODUCT", sequenceName = "SEQ_PRODUCT")
    @Column(name = "ID")
    private Long id;

    @Column(name = "SKU", nullable = false)
    private String sku;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_CATEGORY_ID"), nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "FK_BRAND", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_BRAND_ID"), nullable = false)
    private Brand brand;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatus status;

    public static Product converFrom(ProductRequest request, Product product) {
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(Optional.ofNullable(request.getDescription())
                .orElse(""));
        product.setPrice(request.getPrice());
        product.setStatus(EStatus.A);
        return product;
    }

    public static Product converFromResponse(ProductResponse response) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(response, Product.class);
    }
}