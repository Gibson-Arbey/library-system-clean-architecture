package co.clean_architecture.api.book;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> booksRoutes(BookHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/book", handler::createBookWithCopies)
                .POST("/api/v1/book/copy/{id}", handler::createBookCopies)
                .GET("/api/v1/book", handler::getAllBooksWithCopies)
                .DELETE("/api/v1/book/{id}", handler::deleteBookWithCopies)
                .build();

    }
}
