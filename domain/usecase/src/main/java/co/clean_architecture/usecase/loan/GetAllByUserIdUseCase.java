package co.clean_architecture.usecase.loan;

import co.clean_architecture.model.loan.Loan;
import co.clean_architecture.model.loan.gateways.LoanRepository;
import co.clean_architecture.model.user.exception.UserNotExistsException;
import co.clean_architecture.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllByUserIdUseCase {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public Flux<Loan> execute(Long userId){
        return userRepository.existsById(userId)
            .flatMapMany(exists -> {
                if (exists) {
                    return loanRepository.getAllByUserId(userId);
                } else {
                    return Flux.error(new UserNotExistsException("User with id " + userId + " does not exist."));
                }
            });
    }
}
