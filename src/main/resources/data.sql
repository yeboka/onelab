INSERT INTO `users` (name, age, password)
VALUES ('John Doe', 30, '123'),
       ('Alice Smith', 25, '133'),
       ('Bob Johnson', 28, '123'),
       ('Emma Brown', 22, '123'),
       ('Michael Lee', 35, '123'),
       ('Sophia Wilson', 29, '123'),
       ('James Taylor', 27, '123'),
       ('Olivia Anderson', 31, '123'),
       ('William Martinez', 26, '123'),
       ('Emily Davis', 24, '123');

INSERT INTO `post` (author_id, description, numOfLikes)
VALUES (1, 'This is a post by John Doe', 0),
       (2, 'Check out my new blog!', 0),
       (3, 'I just visited a beautiful place!', 0),
       (4, 'Sharing a recipe...', 0),
       (5, 'My thoughts on the latest movie', 0),
       (6, 'Happy weekend everyone!', 0),
       (7, 'A picture from my vacation', 0),
       (8, 'Excited about my new project!', 0),
       (9, 'Celebrating a special day', 0),
       (10, 'Join me for a live stream', 0);

INSERT INTO `comment` (postId, authorId, text)
VALUES (1, 2, 'Nice post!'),
       (1, 3, 'Great insights!'),
       (1, 5, 'I agree with you.'),
       (2, 1, 'Looking forward to it!'),
       (2, 6, 'Shared it with my friends.'),
       (3, 4, 'Where is this place?'),
       (4, 9, 'Yum! I will try this recipe.'),
       (4, 10, 'Congratulations on your new recipe!'),
       (5, 7, 'I loved that movipostRepository.findAllByAuthor(subscription)e too.'),
       (6, 8, 'Have a great weekend!'),
       (7, 3, 'Beautiful view!'),
       (8, 6, 'Good luck with your project!'),
       (9, 2, 'Happy birthday!'),
       (9, 5, 'Wishing you a wonderful day.'),
       (10, 1, 'I will be there!'),
       (10, 4, 'Looking forward to it.'),
       (10, 7, 'Can nott wait!'),
       (10, 9, 'Count me in!'),
       (10, 10, 'Sounds interesting!');

INSERT INTO `subscriptions` (user_id, subscriber_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 2),
       (6, 3),
       (7, 4),
       (8, 5);
