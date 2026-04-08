CREATE TABLE loans (
    loan_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    boco_id BIGINT NOT NULL,
    loan_loandate TIMESTAMPTZ NOT NULL,
    loan_duedate TIMESTAMPTZ NOT NULL,
    loan_returndate TIMESTAMPTZ,
    loan_status varchar(255) NOT NULL,
    loan_createddate TIMESTAMPTZ NOT NULL,
    CONSTRAINT loans_user_fkey FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT loans_boco_fkey FOREIGN KEY (boco_id) REFERENCES bookcopies(boco_id)
);