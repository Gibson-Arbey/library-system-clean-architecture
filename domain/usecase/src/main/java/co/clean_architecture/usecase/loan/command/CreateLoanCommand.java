package co.clean_architecture.usecase.loan.command;

public record CreateLoanCommand(Long userId, Long bookCopyId) {
}
