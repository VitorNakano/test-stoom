package br.com.stoom.store.category.dto;

import br.com.stoom.store.category.model.Category;
import br.com.stoom.store.common.enums.EStatus;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;
    private EStatus status;

    public static CategoryResponse of(Category category) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(category, CategoryResponse.class);
    }
}
