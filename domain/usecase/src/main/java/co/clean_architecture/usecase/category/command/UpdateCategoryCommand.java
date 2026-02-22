package co.clean_architecture.usecase.category.command;

public record UpdateCategoryCommand(Long id, String name, String description) {
}
