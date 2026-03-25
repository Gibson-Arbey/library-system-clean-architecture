package co.clean_architecture.usecase.user.command;

public record UpdateUserCommand(String username, String password, String email, String roleName) {
}
