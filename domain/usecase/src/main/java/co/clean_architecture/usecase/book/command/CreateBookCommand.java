package co.clean_architecture.usecase.book.command;

import java.util.List;

public record CreateBookCommand(
        String title,
        String author,
        String isbn,
        int publicationYear,
        String publisher,
        Long categoryId,
        List<CreateBookCopyCommand> copies
) {}