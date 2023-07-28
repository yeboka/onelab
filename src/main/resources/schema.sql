-- CREATE SCHEMA IF NOT EXISTS `practice2`;
-- USE `practice2`;


DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE ,
    age  INT,
    password VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE `post`
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    author_id   BIGINT,
    description VARCHAR(255),
    numOfLikes  INT,
    FOREIGN KEY (author_id) REFERENCES `users` (id) ON DELETE CASCADE
);

CREATE TABLE `comment`
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    postId   BIGINT,
    authorId BIGINT,
    text     VARCHAR(255),
    FOREIGN KEY (postId) REFERENCES `post` (id) ON DELETE CASCADE,
    FOREIGN KEY (authorId) REFERENCES `users` (id) ON DELETE CASCADE
);

CREATE TABLE `subscriptions`
(
    user_id       BIGINT,
    subscriber_id BIGINT,
    PRIMARY KEY (user_id, subscriber_id),
    FOREIGN KEY (user_id) REFERENCES `users` (id),
    FOREIGN KEY (subscriber_id) REFERENCES `users` (id)
);

