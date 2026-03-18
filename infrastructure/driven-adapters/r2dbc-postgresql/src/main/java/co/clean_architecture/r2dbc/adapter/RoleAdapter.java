package co.clean_architecture.r2dbc.adapter;

import co.clean_architecture.model.role.Role;
import co.clean_architecture.model.role.gateways.RoleRepository;
import co.clean_architecture.r2dbc.mapper.RoleMapper;
import co.clean_architecture.r2dbc.repository.RoleR2dbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleAdapter implements RoleRepository {

    private final RoleR2dbcRepository roleR2dbcRepository;
    private final RoleMapper roleMapper;

    @Override
    public Mono<Role> findByName(String name) {
        return roleR2dbcRepository
            .findByName(name)
            .map(roleMapper::toEntity);
    }

    @Override
    public Flux<Role> findAllByNameIn(List<String> names) {
        return roleR2dbcRepository
            .findAllByNameIn(names)
            .map(roleMapper::toEntity);
    }
}
