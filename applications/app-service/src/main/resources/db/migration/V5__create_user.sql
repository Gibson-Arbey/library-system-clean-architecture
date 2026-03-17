CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    user_createddate TIMESTAMPTZ NOT NULL,
    user_email varchar(255) NOT NULL,
    user_password varchar(255) NOT NULL,
    user_status varchar(255) NOT NULL,
    user_username varchar(255) NOT NULL,
    CONSTRAINT users_ukey_email UNIQUE (user_email),
    CONSTRAINT users_ukey_username UNIQUE (user_username),
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);