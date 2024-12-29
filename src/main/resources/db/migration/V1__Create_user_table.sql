-- V1__Create_user_table.sql
CREATE TABLE app_user (
    u_id SERIAL PRIMARY KEY,
    u_username VARCHAR(255) NOT NULL,
    u_email VARCHAR(255) NOT NULL,
    u_password VARCHAR(255) NOT NULL,
    u_role VARCHAR(50) NOT NULL
);
