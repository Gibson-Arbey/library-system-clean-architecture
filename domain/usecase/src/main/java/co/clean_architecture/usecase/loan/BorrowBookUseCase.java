package co.clean_architecture.usecase.loan;

import co.clean_architecture.model.book.gateways.BookRepository;
import co.clean_architecture.model.bookcopy.StatusBookCopyEnum;
import co.clean_architecture.model.bookcopy.gateways.BookCopyRepository;
import co.clean_architecture.model.bookcopy.gateways.exception.BookCopyNotFoundException;
import co.clean_architecture.model.loan.Loan;
import co.clean_architecture.model.loan.gateways.LoanRepository;
import co.clean_architecture.model.user.gateways.UserRepository;
import co.clean_architecture.usecase.loan.command.CreateLoanCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class BorrowBookUseCase {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public Mono<Loan> execute(CreateLoanCommand command) {
        return userRepository.findById(command.userId())
            .switchIfEmpty(Mono.error(new IllegalArgumentException("User not found")))
                .flatMap(user ->
                    bookCopyRepository.findById(command.bookCopyId())
                        .switchIfEmpty(Mono.error(new BookCopyNotFoundException("Book copy not found")))
                        .flatMap(bookCopy -> {
                            if (bookCopy.getStatus() != StatusBookCopyEnum.AVAILABLE) {
                                return Mono.error(new IllegalStateException("Book copy is not available"));
                            }
                            return bookRepository.findById(bookCopy.getBookId())
                                .switchIfEmpty(Mono.error(new IllegalArgumentException("Book not found")))
                                .flatMap(book -> {
                                    LocalDateTime now = LocalDateTime.now();

                                    int maxDays = book.getMaxLoanDays() != null
                                        ? book.getMaxLoanDays() : 7;

                                    LocalDateTime dueDate = now.plusDays(maxDays);
                                    Loan loan = Loan.create(
                                        user.getId(),
                                        bookCopy.getId(),
                                        now,
                                        dueDate
                                    );

                                    return bookCopyRepository
                                        .updateStatusByBookCopyId(bookCopy.getId(), StatusBookCopyEnum.BORROWED.name())
                                        .then(loanRepository.save(loan));
                                });
                    })
                );
    }
}
