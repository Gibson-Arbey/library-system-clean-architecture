package co.clean_architecture.r2dbc.mapper;

import co.clean_architecture.model.category.Category;
import co.clean_architecture.r2dbc.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryEntity.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .createdDate(category.getCreatedDate())
            .build();
    }

    public Category toDomain(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        return Category.restore(
            categoryEntity.getId(),
            categoryEntity.getName(),
            categoryEntity.getDescription(),
            categoryEntity.getCreatedDate()
        );
    }

}
