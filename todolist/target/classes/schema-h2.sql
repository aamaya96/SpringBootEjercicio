CREATE TABLE category (
  category_name	VARCHAR(20) PRIMARY KEY
);

CREATE TABLE api_user (
  user_name	        VARCHAR(20) PRIMARY KEY,
  first_name        VARCHAR(25),
  last_name         VARCHAR(25)
);

CREATE TABLE to_do(
  id                INT AUTO_INCREMENT PRIMARY KEY,
  priority 	        INTEGER NOT NULL,
  title 			      VARCHAR(20) NOT NULL ,
  description     	VARCHAR(40),
  user_name		      VARCHAR(20) NOT NULL,
  category_name     VARCHAR(20) NOT NULL ,
  FOREIGN KEY (category_name) REFERENCES category(category_name) ON DELETE CASCADE ,
  FOREIGN KEY (user_name) REFERENCES api_user(user_name) ON DELETE CASCADE
);