package co.clean_architecture.api.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class AuthUserResponse {

    private Long id;
    private String username;
    private String email;
    private String role;

}
