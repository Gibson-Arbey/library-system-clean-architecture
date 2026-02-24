package co.clean_architecture.r2dbc.entity;

import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("bookcopies")
@NoArgsConstructor
@AllArgsConstructor
public class BookCopyEntity {

    @Id
    @Column("boco_id")
    private Long id;

    @Column("book_id")
    private Long bookId;

    @Column("boco_barcode")
    private String barcode;

    @Column("boco_status")
    private String status;

    @Column("boco_createddate")
    private LocalDateTime createdDate;
}
