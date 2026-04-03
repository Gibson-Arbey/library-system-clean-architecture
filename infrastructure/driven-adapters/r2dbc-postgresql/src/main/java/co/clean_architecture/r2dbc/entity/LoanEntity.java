package co.clean_architecture.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table("loans")
@NoArgsConstructor
@AllArgsConstructor
public class LoanEntity {

    @Id
    @Column("loan_id")
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("boco_id")
    private Long bookCopyId;

    @Column("loan_loandate")
    private LocalDateTime loanDate;

    @Column("loan_duedate")
    private LocalDateTime dueDate;

    @Column("loan_returndate")
    private LocalDateTime returnDate;

    @Column("loan_status")
    private String status;

    @Column("loan_createddate")
    private LocalDateTime createdDate;
}
