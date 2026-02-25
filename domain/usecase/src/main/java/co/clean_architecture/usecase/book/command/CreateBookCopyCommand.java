package co.clean_architecture.usecase.book.command;

public record CreateBookCopyCommand(
        String barcode
) {}
