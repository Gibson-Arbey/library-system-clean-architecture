CREATE TABLE categories (
    cate_id BIGSERIAL PRIMARY KEY,
    cate_name VARCHAR(255) NOT NULL,
    cate_description VARCHAR(255) NOT NULL,
    cate_createddate TIMESTAMPTZ  NOT NULL
);