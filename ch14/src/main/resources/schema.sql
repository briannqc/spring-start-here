CREATE TABLE IF NOT EXISTS accounts
(
    uuid   VARCHAR(50) PRIMARY KEY,
    name   VARCHAR(100) NOT NULL,
    amount DOUBLE       NOT NULL
);