package br.com.stoom.store.category.model;

import br.com.stoom.store.category.dto.CategoryRequest;
import br.com.stoom.store.category.dto.CategoryResponse;
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
@Table(name = "CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private static final ModelMapper mapper = new ModelMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CATEGORY")
    @SequenceGenerator(name = "SEQ_CATEGORY", sequenceName = "SEQ_CATEGORY")
    private Long id;

    @Column(name = "CATEGORY_NAME")
    private String name;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    public static Category convertFrom(CategoryRequest request) {
        Category category = mapper.map(request, Category.class);
        category.setStatus(EStatus.A);
        return category;
    }

    public static Category convertFromOptional(Optional<Category> optional) {
        return mapper.map(optional, Category.class);
    }

    public static Category convertFromResponse(CategoryResponse response) {
        return mapper.map(response, Category.class);
    }
}
