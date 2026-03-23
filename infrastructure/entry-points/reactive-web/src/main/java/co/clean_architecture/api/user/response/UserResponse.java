package co.clean_architecture.api.user.response;

import co.clean_architecture.model.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String mail;
    private String status;
    private Long roleId;

    public static UserResponse fromDomain(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .mail(user.getMail().value())
                .status(user.getStatus())
                .roleId(user.getRoleId())
                .build();
    }
}
