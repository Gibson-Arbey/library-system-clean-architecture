package co.clean_architecture.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table("roles")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @Column("role_id")
    private Long id;

    @Column("role_name")
    private String name;

    @Column("role_createddate")
    private LocalDateTime createdDate;
}
