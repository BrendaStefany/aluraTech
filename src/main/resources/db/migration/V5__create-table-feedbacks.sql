CREATE TABLE feedbacks (
  username varchar(20) NOT NULL,
  code varchar(10) NOT NULL,
  note DOUBLE NOT NULL,
  motive varchar(255) NOT NULL,
  PRIMARY KEY (username,code),
  UNIQUE KEY unique_feedbacks (username,code),
  CONSTRAINT feedbacks_fk_username FOREIGN KEY (username) REFERENCES users (username),
  CONSTRAINT feedbacks_fk_code FOREIGN KEY (code) REFERENCES courses (code)
)