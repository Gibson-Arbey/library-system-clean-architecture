package co.clean_architecture.api.user.mapper;

import co.clean_architecture.api.user.request.UpdateUserStatusRequest;
import co.clean_architecture.usecase.user.command.UpdateUserStatusCommand;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserStatusMapper {

    public UpdateUserStatusCommand toCommand(UpdateUserStatusRequest request) {
        return new UpdateUserStatusCommand(request.getId(), request.getStatus());
    }
}
