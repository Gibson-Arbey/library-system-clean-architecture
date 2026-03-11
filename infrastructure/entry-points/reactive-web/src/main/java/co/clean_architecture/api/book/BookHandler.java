package co.clean_architecture.api.book;

import co.clean_architecture.api.book.mapper.CreateBookRequestMapper;
import co.clean_architecture.api.book.request.CreateBookCopyRequest;
import co.clean_architecture.api.book.request.CreateBookRequest;
import co.clean_architecture.api.book.response.BookCopyResponse;
import co.clean_architecture.api.book.response.BookResponse;
import co.clean_architecture.api.book.response.BookWithCopiesResponse;
import co.clean_architecture.model.book.Book;
import co.clean_architecture.model.book.criteria.BookCriteria;
import co.clean_architecture.usecase.book.AddBookCopyUseCase;
import co.clean_architecture.usecase.book.CreateBookWithCopiesUseCase;
import co.clean_architecture.usecase.book.GetAllBookWithCopiesUseCase;
import co.clean_architecture.usecase.book.command.CreateBookCopyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookHandler {

    private final CreateBookWithCopiesUseCase createBookWithCopiesUseCase;
    private final AddBookCopyUseCase addBookCopyUseCase;
    private final GetAllBookWithCopiesUseCase getAllBookWithCopiesUseCase;

    // mappers
    private final CreateBookRequestMapper createBookRequestMapper;

    public Mono<ServerResponse> createBookWithCopies(ServerRequest request) {
        return request.bodyToMono(CreateBookRequest.class)
                .map(createBookRequestMapper::toCommand
                )
            .flatMap(createBookWithCopiesUseCase::execute)
            .flatMap(book -> toResponse(book, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> createBookCopies(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToFlux(CreateBookCopyRequest.class)
                .map(copyReq -> new CreateBookCopyCommand(copyReq.getBarcode()))
                .collectList()
                .flatMap(commands -> addBookCopyUseCase.execute(id, commands).collectList())
                .flatMap(copies -> ServerResponse.ok()
                        .bodyValue(copies.stream()
                                .map(BookCopyResponse::fromDomain)
                                .toList()));
    }

    public Mono<ServerResponse> getAllBooksWithCopies(ServerRequest request) {

        BookCriteria criteria = BookCriteria.of(
                request.queryParam("title").orElse(null),
                request.queryParam("author").orElse(null),
                request.queryParam("isbn").orElse(null),

                request.queryParams()
                        .getOrDefault("publicationYear", List.of())
                        .stream()
                        .map(Integer::valueOf)
                        .toList(),

                request.queryParam("publisher").orElse(null),

                request.queryParams()
                        .getOrDefault("categoryId", List.of())
                        .stream()
                        .map(Long::valueOf)
                        .toList(),

                request.queryParam("page")
                        .map(Integer::valueOf)
                        .orElse(null),

                request.queryParam("pageSize")
                        .map(Integer::valueOf)
                        .orElse(null)
        );

        return getAllBookWithCopiesUseCase.execute(criteria)
                .map(BookWithCopiesResponse::fromDomain)
                .collectList()
                .flatMap(response ->
                        ServerResponse.ok().bodyValue(response)
                );
    }

    private Mono<ServerResponse> toResponse(Book book, HttpStatus status) {
        return ServerResponse
                .status(status)
                .bodyValue(BookResponse.fromDomain(book));
    }
}
