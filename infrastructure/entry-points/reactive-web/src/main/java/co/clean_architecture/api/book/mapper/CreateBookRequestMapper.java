package co.clean_architecture.api.book.mapper;

import co.clean_architecture.api.book.request.CreateBookRequest;
import co.clean_architecture.usecase.book.command.CreateBookCommand;
import co.clean_architecture.usecase.book.command.CreateBookCopyCommand;
import org.springframework.stereotype.Component;

@Component
public class CreateBookRequestMapper {

    public CreateBookCommand toCommand(CreateBookRequest request) {
        return new CreateBookCommand(
                request.getTitle(),
                request.getAuthor(),
                request.getIsbn(),
                request.getPublicationYear(),
                request.getPublisher(),
                request.getCategoryId(),
                request.getCopies().stream()
                        .map(copyReq -> new CreateBookCopyCommand(copyReq.getBarcode()))
                        .toList()
        );
    }
}
