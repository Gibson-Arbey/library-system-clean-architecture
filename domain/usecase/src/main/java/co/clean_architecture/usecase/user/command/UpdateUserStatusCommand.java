package co.clean_architecture.usecase.user.command;

public record UpdateUserStatusCommand(Long id, String status) {
}
