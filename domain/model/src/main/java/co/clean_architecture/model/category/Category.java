package co.clean_architecture.model.category;

import co.clean_architecture.model.exception.InvalidFieldException;

import java.time.LocalDateTime;


public class Category {

    private Long id;

    private final String name;

    private final String description;

    private final LocalDateTime createdDate;

    private Category(Long id, String name, String description, LocalDateTime createdDate) {

        if(name == null || name.isBlank()) {
            throw new InvalidFieldException("Name cannot be null or blank");
        }

        if (description == null || description.isBlank()) {
            throw new InvalidFieldException("Description cannot be null or blank");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
    }

    public static Category create(String name, String description) {
        return new Category(null, name, description, LocalDateTime.now());
    }

    public static Category restore(Long id, String name, String description, LocalDateTime createdDate) {
        return new Category(id, name, description, createdDate);

    }

    public Category withUpdatedValues(String name, String description) {
        return new Category(this.id, name, description, this.createdDate);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
