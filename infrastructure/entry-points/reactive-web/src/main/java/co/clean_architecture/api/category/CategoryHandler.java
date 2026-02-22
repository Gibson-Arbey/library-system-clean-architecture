package co.clean_architecture.api.category;

import co.clean_architecture.api.category.request.CreateCategoryRequest;
import co.clean_architecture.api.category.response.CategoryResponse;
import co.clean_architecture.model.category.Category;
import co.clean_architecture.usecase.category.*;
import co.clean_architecture.usecase.category.command.CreateCategoryCommand;
import co.clean_architecture.usecase.category.command.UpdateCategoryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoryHandler {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetAllCategoryUseCase getAllCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public Mono<ServerResponse> createCategory(ServerRequest request) {
        return request.bodyToMono(CreateCategoryRequest.class)
                .map(req -> new CreateCategoryCommand(req.getName(), req.getDescription()))
            .flatMap(createCategoryUseCase::execute)
            .flatMap(category -> toResponse(category, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> getAllCategories(ServerRequest request) {
        return getAllCategoryUseCase.execute()
            .map(CategoryResponse::fromDomain)
            .collectList()
            .flatMap(categories -> ServerResponse.ok().bodyValue(categories));
    }

    public Mono<ServerResponse> getCategoryById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return getCategoryByIdUseCase.execute(id)
            .flatMap(category -> toResponse(category, HttpStatus.OK));
    }

    public Mono<ServerResponse> updateCategory(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(CreateCategoryRequest.class)
                .map(req -> new UpdateCategoryCommand(id, req.getName(), req.getDescription()))
            .flatMap(updateCategoryUseCase::execute)
            .flatMap(category -> toResponse(category, HttpStatus.OK));
    }

    public Mono<ServerResponse> deleteCategory(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return deleteCategoryUseCase.execute(id)
            .then(ServerResponse.noContent().build());
    }

    private Mono<ServerResponse> toResponse(Category category, HttpStatus status) {
        return ServerResponse
            .status(status)
            .bodyValue(CategoryResponse.fromDomain(category));
    }
}
