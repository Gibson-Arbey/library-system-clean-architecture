package co.clean_architecture.api.user.mapper;

import co.clean_architecture.api.user.request.CreateUserRequest;
import co.clean_architecture.usecase.user.command.CreateUserCommand;
import org.springframework.stereotype.Component;

@Component
public class CreateUserRequestMapper {

    public CreateUserCommand toCreateUserCommand(CreateUserRequest request) {
        return new CreateUserCommand(
            request.getUsername(),
            request.getPassword(),
            request.getMail(),
            request.getRoleName()
        );
    }
}
