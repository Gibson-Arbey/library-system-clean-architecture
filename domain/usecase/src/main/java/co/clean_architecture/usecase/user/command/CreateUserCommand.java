package co.clean_architecture.usecase.user.command;

public record CreateUserCommand(String username, String password, String mail, String roleName) {
}
