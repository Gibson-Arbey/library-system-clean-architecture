CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    user_createddate TIMESTAMPTZ NOT NULL,
    user_mail varchar(255) NOT NULL,
    user_password varchar(255) NOT NULL,
    user_status varchar(255) NOT NULL,
    user_username varchar(255) NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT users_ukey_email UNIQUE (user_mail),
    CONSTRAINT users_ukey_username UNIQUE (user_username),
    CONSTRAINT users_role_fkey FOREIGN KEY (role_id) REFERENCES roles(role_id)
);