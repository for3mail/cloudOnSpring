SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id                    INT(11) NOT NULL AUTO_INCREMENT,
  username              VARCHAR(50) NOT NULL,
  password              CHAR(80) NOT NULL,
  first_name            VARCHAR(50) NOT NULL,
  last_name             VARCHAR(50) NOT NULL,
  email                 VARCHAR(50) NOT NULL,

  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id                    INT(11) NOT NULL AUTO_INCREMENT,
  name                  VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id               INT(11) NOT NULL,
  role_id               INT(11) NOT NULL,

  PRIMARY KEY (user_id, role_id),

--  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
  REFERENCES users (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
  REFERENCES roles (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS cloud_files;

CREATE TABLE cloud_files (
  id	                INT(11) NOT NULL AUTO_INCREMENT,
  user_id               INT(11) NOT NULL,
  title                 VARCHAR(255) NOT NULL,
  file_path             VARCHAR(255) NOT NULL,
  created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_USER_ID_FILE FOREIGN KEY (user_id)
  REFERENCES users (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO roles (name)
VALUES
('ROLE_EMPLOYEE'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (username,password,first_name,last_name,email)
VALUES
('admin','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Admin','Admin','admin@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);

--INSERT INTO cloud_files (user_id, title, file_path)
--VALUES
--(1, "Первый файл", "cloud1.txt"),
--(1, "Второй файл", "cloud2.txt"),
--(1, "Третий файл", "C:cloud3.txt"),
--(1, "Лучший файл", "C:cloud4.txt"),
--(1, "Умный файл", "C:cloud5.txt"),
--(1, "шестой файл", "C:cloud6.txt"),
--(1, "седьмой файл", "C:cloud7.txt"),
--(1, "восьмой файл", "C:cloud8.txt"),
--(1, "девятый файл", "C:cloud9.txt"),
--(1, "десятый файл", "C:cloud10.txt");



