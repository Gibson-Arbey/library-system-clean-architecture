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
            .POST("/category", handler::createCategory)
            .GET("/category/{id}", handler::getCategoryById)
            .GET("/category", handler::getAllCategories)
            .PUT("/category/{id}", handler::updateCategory)
            .DELETE("/categoriy/{id}", handler::deleteCategory)
            .build();

    }
}
