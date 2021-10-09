CREATE TABLE IF NOT EXISTS user_tb (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255) ,
    authority VARCHAR(255),
    is_enabled BOOLEAN NOT NULL,
    is_account_non_expired BOOLEAN NOT NULL,
    is_account_non_locked BOOLEAN NOT NULL,
    is_credentials_non_expired BOOLEAN NOT NULL
);