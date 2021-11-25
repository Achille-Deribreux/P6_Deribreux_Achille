DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  id int NOT NULL AUTO_INCREMENT,
  senderid int NOT NULL,
  receiverid int NOT NULL,
  amount int NOT NULL,
  datestamp DATETIME NOT NULL, 
  description varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_senderid FOREIGN KEY (senderid) REFERENCES users(id)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  id int NOT NULL AUTO_INCREMENT,
  firstname varchar(255) NOT NULL,
  lastname varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  balance int NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `connections`;
CREATE TABLE `connections` (
  id int NOT NULL AUTO_INCREMENT,
  userid int NOT NULL,
  friendid int NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_connectionuserid FOREIGN KEY (userid) REFERENCES users(id), 
  CONSTRAINT fk_connectionfriendid FOREIGN KEY (friendid) REFERENCES users(id)
);

//TODO : Create table connections & bankaccount