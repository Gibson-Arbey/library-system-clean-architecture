package co.clean_architecture.r2dbc.mapper;

import co.clean_architecture.model.role.Role;
import co.clean_architecture.r2dbc.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleEntity toEntity(Role role) {
        if(role == null) return null;

        return RoleEntity
            .builder()
            .id(role.getId())
            .name(role.getName())
            .createdDate(role.getCreatedDate())
            .build();
    }

    public Role toEntity(RoleEntity roleEntity) {
        if(roleEntity == null) return null;

        return Role.restore(
            roleEntity.getId(),
            roleEntity.getName(),
            roleEntity.getCreatedDate()
        );
    }
}
