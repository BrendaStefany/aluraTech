ALTER TABLE alura_tech.registrations CHANGE register_user username varchar(20) NOT NULL;

ALTER TABLE alura_tech.registrations CHANGE register_course code varchar(10) NOT NULL;

ALTER TABLE registrations MODIFY COLUMN id INT;

ALTER TABLE registrations DROP PRIMARY KEY;

ALTER TABLE registrations ADD PRIMARY KEY (username, code);

ALTER TABLE registrations DROP COLUMN id;