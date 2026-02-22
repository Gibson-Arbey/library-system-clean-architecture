package co.clean_architecture.api.category.response;

import co.clean_architecture.model.category.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {

    private final Long id;

    private final String name;

    private final String description;

    private CategoryResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

        public static CategoryResponse fromDomain(Category category) {
            return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription());
        }
}
