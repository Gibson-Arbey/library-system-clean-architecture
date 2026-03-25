package co.clean_architecture.api.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserStatusRequest {
    private Long id;
    private String status;
}
