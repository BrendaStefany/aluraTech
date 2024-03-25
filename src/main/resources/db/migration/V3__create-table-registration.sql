CREATE TABLE registrations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    register_user VARCHAR(20) NOT NULL,
    register_course VARCHAR(10) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (register_user) REFERENCES users(username),
    FOREIGN KEY (register_course) REFERENCES courses(code),
    CONSTRAINT unique_registration UNIQUE (register_user, register_course)
);