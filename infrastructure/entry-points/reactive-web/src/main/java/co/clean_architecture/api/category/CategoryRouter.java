package co.clean_architecture.api.category;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CategoryRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(CategoryHandler handler) {
        return RouterFunctions.route()
            .POST("/api/v1/category", handler::createCategory)
            .GET("/api/v1/category/{id}", handler::getCategoryById)
            .GET("/api/v1/category", handler::getAllCategories)
            .PUT("/api/v1/category/{id}", handler::updateCategory)
            .DELETE("/api/v1/categoriy/{id}", handler::deleteCategory)
            .build();

    }
}
