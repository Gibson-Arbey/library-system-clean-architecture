package co.clean_architecture.api.user;

import co.clean_architecture.api.user.mapper.CreateUserRequestMapper;
import co.clean_architecture.api.user.mapper.UpdateUserRequestMapper;
import co.clean_architecture.api.user.mapper.UpdateUserStatusRequestMapper;
import co.clean_architecture.api.user.request.CreateUserRequest;
import co.clean_architecture.api.user.request.UpdateUserRequest;
import co.clean_architecture.api.user.request.UpdateUserStatusRequest;
import co.clean_architecture.api.user.response.UserResponse;
import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.criteria.UserCriteria;
import co.clean_architecture.usecase.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUserByFiltersUseCase getAllUserByFiltersUseCase;
    private final UpdateUserStatusUseCase updateUserStatusUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    // mappers
    private final CreateUserRequestMapper createUserRequestMapper;
    private final UpdateUserStatusRequestMapper updateUserStatusRequestMapper;
    private final UpdateUserRequestMapper updateUserRequestMapper;

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserRequest.class)
                .map(createUserRequestMapper::toCreateUserCommand)
                .flatMap(createUserUseCase::execute)
                .flatMap(user -> toResponse(user, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        return getUserByIdUseCase
            .execute(id)
            .flatMap(user -> toResponse(user, HttpStatus.OK));
    }

    public Mono<ServerResponse> updateUserStatus(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UpdateUserStatusRequest.class)
            .map(updateUserStatusRequestMapper::toCommand)
            .flatMap(updateUserStatusUseCase::execute)
            .then(ServerResponse.status(HttpStatus.NO_CONTENT).build());
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        Long id = Long.valueOf(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(UpdateUserRequest.class)
            .map(updateUserRequestMapper::toCommand)
            .flatMap(command -> updateUserUseCase.execute(id, command))
            .flatMap(user -> toResponse(user, HttpStatus.OK));
    }

    public Mono<ServerResponse> getAllUserByFilters(ServerRequest serverRequest) {
        String username = serverRequest.queryParam("username").orElse(null);
        String mail = serverRequest.queryParam("email").orElse(null);

        List<String> statuses = serverRequest.queryParams().getOrDefault("status", List.of());
        List<Long> roleIds = serverRequest.queryParams()
                .getOrDefault("roleId", List.of())
                .stream()
                .map(Long::valueOf)
                .toList();

        Integer limit = serverRequest.queryParam("limit")
                .map(Integer::valueOf)
                .orElse(null);

        Integer offset = serverRequest.queryParam("offset")
                .map(Integer::valueOf)
                .orElse(null);

        UserCriteria criteria = UserCriteria.of(
                username,
                mail,
                statuses,
                roleIds,
                limit,
                offset
        );


        return getAllUserByFiltersUseCase.execute(criteria)
            .collectList()
            .flatMap(users ->
                    ServerResponse.ok().bodyValue(users.stream().map(UserResponse::fromDomain).toList()));
    }

    private Mono<ServerResponse> toResponse(User user, HttpStatus status) {
        return ServerResponse
                .status(status)
                .bodyValue(UserResponse.fromDomain(user));
    }
}
