CREATE TABLE user_roles (
    usro_id BIGSERIAL PRIMARY KEY,
    user_id int8 NOT NULL,
    role_id int8 NOT NULL,
    usro_createddate TIMESTAMPTZ NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (usro_id),
    CONSTRAINT user_roles_ukey_role FOREIGN KEY (role_id) REFERENCES comunity.roles(role_id),
    CONSTRAINT user_roles_ukey_user FOREIGN KEY (user_id) REFERENCES comunity.users(user_id)
);