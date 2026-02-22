package co.clean_architecture.r2dbc.entity;

import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@Builder
@Table("categories")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @Column("cate_id")
    private Long id;

    @Column("cate_name")
    private String name;

    @Column("cate_description")
    private String description;

    @Column("cate_createddate")
    private LocalDateTime createdDate;


}
