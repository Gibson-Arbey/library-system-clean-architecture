package co.clean_architecture.api.category.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {

    private String name;

    private String description;
}
