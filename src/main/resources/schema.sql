-- CREATE SCHEMA IF NOT EXISTS `practice2`;
-- USE `practice2`;


DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age  INT
);

CREATE TABLE `post`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    author_id   BIGINT,
    description VARCHAR(255),
    numOfLikes  INT,
    FOREIGN KEY (author_id) REFERENCES `user` (id)
);

CREATE TABLE `comment`
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    postId   BIGINT,
    authorId BIGINT,
    text     VARCHAR(255),
    FOREIGN KEY (postId) REFERENCES `post` (id),
    FOREIGN KEY (authorId) REFERENCES `user` (id)
);
