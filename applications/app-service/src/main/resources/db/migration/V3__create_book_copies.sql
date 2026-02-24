CREATE TABLE bookcopies (
    boco_id BIGSERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    boco_barcode VARCHAR(255) NOT NULL,
    boco_status VARCHAR(255) NOT NULL,
    boco_createddate TIMESTAMPTZ  NOT NULL
);