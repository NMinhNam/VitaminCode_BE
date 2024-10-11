create database vitamincode_class;

use vitamincode_class;

CREATE TABLE class
(
    class_id          INT AUTO_INCREMENT PRIMARY KEY,
    class_name        VARCHAR(50) NOT NULL,
    class_description VARCHAR(255)
);

-- Table for Students
CREATE TABLE students
(
    student_id    INT AUTO_INCREMENT PRIMARY KEY,
    student_name  VARCHAR(100) NOT NULL,
    student_email VARCHAR(100),
    class_id      INT,
    FOREIGN KEY (class_id) REFERENCES class (class_id)
);

INSERT INTO class (class_name, class_description)
VALUES ('Math 101', 'Basic Mathematics Class'),
       ('Science 101', 'Introduction to Science'),
       ('History 101', 'World History Overview');


-- Insert data into students table
INSERT INTO students (student_name, student_email, class_id)
VALUES ('Alice Johnson', 'alice.johnson@example.com', 4),
       ('Bob Smith', 'bob.smith@example.com', 2),
       ('Charlie Brown', 'charlie.brown@example.com', 1),
       ('David Lee', 'david.lee@example.com', 3);

CREATE TABLE `role` (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL UNIQUE,
                        description VARCHAR(255)
);

CREATE TABLE `user` (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        password VARCHAR(100) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        enabled BOOLEAN DEFAULT TRUE,
                        role_id BIGINT,
                        FOREIGN KEY (role_id) REFERENCES `role`(id) ON DELETE SET NULL
);

INSERT INTO `role` (name, description) VALUES
                                           ('ADMIN', 'Administrator with full permissions'),
                                           ('USER', 'Standard user with limited permissions'),
                                           ('MANAGER', 'Manager with extended permissions');


INSERT INTO `user` (username, password, email, enabled, role_id) VALUES
                                                                     ('vitamincode01', '$2a$12$.R0rjHgqaAEkmVlovBUtJO8thl36eXVvUQ7RF.7IRYFfEBSiLRhzW', 'john.doe@example.com', TRUE, 1), -- ADMIN
                                                                     ('root', '$2a$12$k3HMaRHpuOrAszjIr2Rys.jbvY.jjNwu5CE5SihxOUULHZGEjJRSC', 'jane.smith@example.com', TRUE, 2), -- USER
                                                                     ('minhnam', '$2a$12$1b9bq4pmBiPj3c003EJft.WRjQ/nT9CosLgI1zeRmOBIT4y.QAC5O', 'mike.brown@example.com', FALSE, 3); -- MANAGER