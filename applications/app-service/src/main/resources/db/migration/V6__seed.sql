-- =========================
-- ROLES
-- =========================
INSERT INTO roles (role_name, role_createddate)
VALUES
    ('ADMIN', NOW()),
    ('STUDENT', NOW());

-- =========================
-- CATEGORIES
-- =========================
INSERT INTO categories (cate_name, cate_description, cate_createddate)
VALUES
    ('Programming', 'Books about programming', NOW()),
    ('Database', 'Books about databases', NOW());

-- =========================
-- BOOKS
-- =========================
INSERT INTO books (book_title, book_author, book_isbn, book_publicationyear, book_publisher, cate_id, book_createddate)
VALUES
    ('Clean Code', 'Robert C. Martin', '9780132350884', 2008, 'Prentice Hall', 1, NOW()),
    ('Designing Data-Intensive Applications', 'Martin Kleppmann', '9781449373320', 2017, 'O''Reilly', 2, NOW());

-- =========================
-- BOOK COPIES
-- =========================
INSERT INTO bookcopies (book_id, boco_barcode, boco_status, boco_createddate)
VALUES
    (1, 'BC-0001', 'AVAILABLE', NOW()),
    (1, 'BC-0002', 'BORROWED', NOW()),
    (2, 'BC-0003', 'AVAILABLE', NOW());

-- =========================
-- USERS
-- =========================

-- ADMIN
INSERT INTO users (
    user_createddate,
    user_mail,
    user_password,
    user_status,
    user_username,
    role_id
)
VALUES (
           NOW(),
           'admin@mail.com',
           '$2a$10$ixuLIJFNtgzmkuR21aisuOztlJuL3p1ILduP9zt22j/fjarrlMJAq',
           'ACTIVE',
           'admin',
           1
       );

-- USER STUDENT
INSERT INTO users (
    user_createddate,
    user_mail,
    user_password,
    user_status,
    user_username,
    role_id
)
VALUES (
           NOW(),
           'student@mail.com',
           '$2a$10$ixuLIJFNtgzmkuR21aisuOztlJuL3p1ILduP9zt22j/fjarrlMJAq',
           'ACTIVE',
           'student',
           2
       );