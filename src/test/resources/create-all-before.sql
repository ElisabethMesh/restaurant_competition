DELETE FROM meals;
DELETE FROM restaurants;
DELETE FROM votes;
DELETE FROM users;

insert into users(id, username, password, firstname, lastname, role, status, created, updated) VALUES
                                         (1, 'lisa', '$2a$12$XXpOOI.IceIXM5Qyf7EtruLI8ryt0eDKy2zghBAMpYEQ6HU2Gzbxa',
                                         'Lisa', 'Meshalkina', 'ADMIN', 'ACTIVE', '2022.07.18 20:00', '2022.07.19 20:00'),
                                         (2, 'igor', '$2a$12$go6ZefdAQnnj1K891a5ug.ZKOHfppQQtI6N3t1DwHfdVAcGlqEPjO',
                                         'Igor', 'Meshalkin', 'USER', 'ACTIVE', '2022.07.18 21:00', '2022.07.19 21:00'),
                                         (3, 'misha', '$2a$12$/f5BFpN0WjmbN7u/4QYFBOGoN782vZTAjhym/Aq417bVLJk8GfxBa',
                                         'Michael', 'Popov', 'USER', 'ACTIVE', '2022.07.18 21:00', '2022.07.19 21:00');

insert into restaurants(id, name) VALUES
                                      (1, 'Panda'),
                                      (2, 'Burger King'),
                                      (3, 'Donuts');



insert into meals(id, name, price, restaurant_id) VALUES
                                                      (1, 'Chicken with oranges', 700, 1),
                                                      (2, 'Green tea', 150, 1),
                                                      (3, 'Burger', 250, 2),
                                                      (4, 'French fries', 150, 2),
                                                      (5, 'Coffee', 70, 3),
                                                      (6, 'Donut', 120, 3);


ALTER TABLE users AUTO_INCREMENT = 0;
ALTER TABLE restaurants AUTO_INCREMENT = 0;
ALTER TABLE meals AUTO_INCREMENT = 0;
ALTER TABLE votes AUTO_INCREMENT = 0;
ALTER TABLE votes DISABLE KEYS;