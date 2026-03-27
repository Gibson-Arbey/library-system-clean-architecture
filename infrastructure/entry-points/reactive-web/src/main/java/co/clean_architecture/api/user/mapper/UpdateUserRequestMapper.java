package co.clean_architecture.api.user.mapper;

import co.clean_architecture.api.user.request.UpdateUserRequest;
import co.clean_architecture.usecase.user.command.UpdateUserCommand;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserRequestMapper {

    public UpdateUserCommand toCommand(UpdateUserRequest request) {
        return new UpdateUserCommand(request.getUsername(), request.getPassword(), request.getMail());
    }
}
