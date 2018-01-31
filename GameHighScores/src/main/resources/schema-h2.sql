CREATE TABLE category (
  category_name	VARCHAR(20) PRIMARY KEY
);

CREATE TABLE PLAYER (
  user_name	VARCHAR(40) PRIMARY KEY
);

CREATE TABLE CATEGORY_DETAIL(
	category_detail_id INT AUTO_INCREMENT PRIMARY KEY,
	category_name 	VARCHAR(20) NOT NULL,
	user_name		VARCHAR(40) NOT NULL,
	level 			INT,
	score  			BIGINT,
	FOREIGN KEY (category_name) REFERENCES CATEGORY(CATEGORY_NAME) ON DELETE CASCADE ,
	FOREIGN KEY (user_name) REFERENCES PLAYER(user_name) ON DELETE CASCADE,
	UNIQUE(category_name,user_name)
);
