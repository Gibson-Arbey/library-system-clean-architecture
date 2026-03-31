package co.clean_architecture.usecase.user;

import co.clean_architecture.model.user.User;
import co.clean_architecture.model.user.criteria.UserCriteria;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllUserByFiltersUseCase {

    private final UserRepository userRepository;

    public Flux<User> execute(UserCriteria criteria) {
        return userRepository.findAllByFilters(criteria);
    }
}
