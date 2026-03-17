package co.clean_architecture.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column("user_id")
    private Long id;

    @Column("user_username")
    private String username;

    @Column("user_password")
    private String password;

    @Column("user_email")
    private String email;

    @Column("user_status")
    private String status;

    @Column("user_createddate")
    private LocalDateTime createdDate;
}
