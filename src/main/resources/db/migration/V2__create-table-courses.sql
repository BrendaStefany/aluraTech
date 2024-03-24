CREATE TABLE courses (
    code VARCHAR(10) NOT NULL,
    name VARCHAR(255) NOT NULL,
    instructor_username VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(255) DEFAULT 'ACTIVE' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    inactive_at TIMESTAMP,
    PRIMARY KEY (code),
    CONSTRAINT fk_instructor FOREIGN KEY (instructor_username) REFERENCES users (username)
);
