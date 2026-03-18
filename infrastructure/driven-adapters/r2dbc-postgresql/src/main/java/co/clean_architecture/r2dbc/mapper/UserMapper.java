package co.clean_architecture.r2dbc.mapper;

import co.clean_architecture.model.user.Mail;
import co.clean_architecture.model.user.Password;
import co.clean_architecture.model.user.User;
import co.clean_architecture.r2dbc.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if(entity == null) return null;
        return User.restore(
            entity.getId(),
            entity.getUsername(),
            new Password(entity.getPassword()),
            new Mail(entity.getMail()),
            entity.getStatus(),
            entity.getRoleId(),
            entity.getCreatedDate()
        );
    }

    public UserEntity toEntity(User domain) {
        if(domain == null) return null;
        return UserEntity
            .builder()
            .id(domain.getId())
            .username(domain.getUsername())
            .password(domain.getPassword().value())
            .mail(domain.getMail().value())
            .status(domain.getStatus())
            .roleId(domain.getRoleId())
            .createdDate(domain.getCreatedDate())
            .build();

    }
}
