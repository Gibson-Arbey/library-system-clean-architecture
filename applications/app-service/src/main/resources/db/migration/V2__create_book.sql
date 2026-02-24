CREATE TABLE books (
    book_id BIGSERIAL PRIMARY KEY,
    book_title VARCHAR(255) NOT NULL,
    book_author VARCHAR(255) NOT NULL,
    book_isbn VARCHAR(255) NOT NULL,
    book_publicationyear NUMERIC(4,0) NOT NULL,
    book_publisher VARCHAR(255) NOT NULL,
    cate_id BIGINT NOT NULL,
    book_createddate TIMESTAMPTZ  NOT NULL
);