CREATE DATABASE IF NOT EXISTS `finalexams1`;
USE `finalexams1`;

CREATE TABLE IF NOT EXISTS `roles`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`rolename` VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS `groups`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`groupname` VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS `users`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`username` VARCHAR(100) ,
	`pwd` VARCHAR(50),
	`role_id` INT ,
	`token` VARCHAR(256),
	`group_id` INT,
	`remote_adr` CHAR(16),
	`forward` CHAR(16),
	`image` VARCHAR(256),
	CONSTRAINT role_users FOREIGN KEY (role_id) REFERENCES `roles`(id),
	CONSTRAINT group_users FOREIGN KEY (group_id) REFERENCES `groups`(id)
);

CREATE TABLE IF NOT EXISTS `addresses`( 
		`id` 				INT PRIMARY KEY AUTO_INCREMENT, 
		`houseno` 			VARCHAR(150), 
		`streetno` 		VARCHAR(150), 
		`streetname` 		VARCHAR(150), 
		`villagename` 	VARCHAR(150), 
		`districtname` 	VARCHAR(150), 
		`communename` 	VARCHAR(150), 
		`provincename` 	VARCHAR(150), 
		`cityname` 		VARCHAR(150), 
		`countryname` 	VARCHAR(150), 
		`iscurrent` 		TINYINT
);

CREATE TABLE IF NOT EXISTS `publishers`(
	`id` 				INT PRIMARY KEY AUTO_INCREMENT,
	`publisername` 	VARCHAR(50),
	`address_id` 		INT,
	CONSTRAINT publishers_address FOREIGN KEY (address_id) REFERENCES `addresses`(id)
);

CREATE TABLE IF NOT EXISTS `categories`(
	`id` 				INT PRIMARY KEY AUTO_INCREMENT,
	`categoryname` 	VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS `books`(
	`id` 				INT PRIMARY KEY AUTO_INCREMENT,
	`title` 			VARCHAR(256),
	`path` 				VARCHAR(256),
	`user_id` 			INT,
	`group_id` 			INT,
	`publisher_id` 		INT,
	CONSTRAINT user_book FOREIGN KEY (user_id) REFERENCES `users`(id),
	CONSTRAINT group_book FOREIGN KEY (group_id) REFERENCES `groups`(id),
	CONSTRAINT publisher_book FOREIGN KEY (publisher_id) REFERENCES `publishers`(id)
);

CREATE TABLE IF NOT EXISTS `books_categories`(
	`book_id`		INT,
	`category_id` 	INT,
	CONSTRAINT book_link_id FOREIGN KEY (book_id) REFERENCES `books`(id),
	CONSTRAINT category_link_id FOREIGN KEY (category_id) REFERENCES `categories`(id)
);

CREATE TABLE IF NOT EXISTS `inventories`(
	id 				INT PRIMARY KEY AUTO_INCREMENT,
	book_id 			INT,
	copies 			INT,
	srcurl 			VARCHAR(256),
	created_at 		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT books_inventories FOREIGN KEY (book_id) REFERENCES `books`(id)
);

CREATE TABLE IF NOT EXISTS `downloads`(
	id 					INT PRIMARY KEY AUTO_INCREMENT,
	user_id 			INT,
	book_id 			INT,
	downloaded_at 		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	CONSTRAINT user_download FOREIGN KEY (user_id) REFERENCES `users`(id),
	CONSTRAINT book_user_download FOREIGN KEY (book_id) REFERENCES `books`(id)
);