-- Drop tables
DROP TABLE if exists user_roles;
DROP TABLE if exists roles;
DROP TABLE if exists users;

-- Table: users
CREATE TABLE users
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(100) NOT NULL
)
    ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
)
    ENGINE = InnoDB;

-- Insert data
INSERT INTO users
VALUES (1, 'Alex', '$2a$10$q/LL/.ZpVfBeVZa6jjS4kex8Uy3nga3JTV.ZbbLlBkXNqLnfILreK', 'San@mail.ru'); -- SET PASSWORD = San
INSERT INTO users
VALUES (2, 'test', '$2a$10$xFBkgR.Pe45fZGdkM2rnaevYwFT6kNb/xsx9AsMWdcu7VEfGa3Wx6', 'test@test.ru'); -- SET PASSWORD = test
INSERT INTO users
VALUES (3, 'ADMIN', '$2a$10$feHNYWuhu4rVSFw9QWTF7O5ds.G1fuO15nU6pwwICy00QZTYNUuAy', 'admin@ru'); -- SET PASSWORD = ADMIN

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles
VALUES (1, 2);
INSERT INTO user_roles
VALUES (2, 1);
INSERT INTO user_roles
VALUES (3, 2);