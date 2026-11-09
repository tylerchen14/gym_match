CREATE TABLE users (
    id            VARCHAR(50)  PRIMARY KEY,
    account       VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    gender        VARCHAR(255),
    birthday      DATE,
    role          VARCHAR(255) NOT NULL,
    last_login    TIMESTAMPTZ,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP
);