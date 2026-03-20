package co.clean_architecture.model.role.gateways;

import co.clean_architecture.model.role.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleRepository {

    Mono<Role> findById(Long id);

    Mono<Role> findByName(String name);

    Flux<Role> findAllByNameIn(List<String> names);

}
