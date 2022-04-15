DROP DATABASE akdemy;

CREATE DATABASE akdemy;
USE akdemy;

CREATE TABLE user (
		email varchar(45),
        first_name varchar(45),
		last_name varchar(45),
        password varchar(120),
        username varchar(45)
);

CREATE TABLE role (
		description varchar(45),
        name varchar(45)
);

CREATE TABLE user_roles (
		user_id int,
        role_id int
);


INSERT INTO user (email, first_name, last_name, password, username) VALUES ('admin@admin.com', 'admin', 'admin', '$2a$04$n6WIRDQlIByVFi.5rtQwEOTAzpzLPzIIG/O6quaxRKY2LlIHG8uty', 'admin');
INSERT INTO role (description, name) VALUES ('ROLE_ADMIN', 'ADMIN');
INSERT INTO role (description, name) VALUES ('ROLE_USER', 'USUARIO');
INSERT INTO role (description, name) VALUES ('ROLE_SUPERVISOR', 'SUPERVISOR');
INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1');