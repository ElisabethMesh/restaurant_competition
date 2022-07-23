delete from users;

insert into users(id, username, password, firstname, lastname, role, status, created, updated) VALUES
                              (1, 'lisa', '$2a$12$XXpOOI.IceIXM5Qyf7EtruLI8ryt0eDKy2zghBAMpYEQ6HU2Gzbxa',
                              'Lisa', 'Meshalkina', 'ADMIN', 'ACTIVE', '2022.07.18 20:00', '2022.07.19 20:00'),
                              (2, 'igor', '$2a$12$go6ZefdAQnnj1K891a5ug.ZKOHfppQQtI6N3t1DwHfdVAcGlqEPjO',
                              'Igor', 'Meshalkin', 'USER', 'ACTIVE', '2022.07.18 21:00', '2022.07.19 21:00'),
                              (3, 'misha', '$2a$12$/f5BFpN0WjmbN7u/4QYFBOGoN782vZTAjhym/Aq417bVLJk8GfxBa',
                              'Michael', 'Popov', 'USER', 'ACTIVE', '2022.07.18 21:00', '2022.07.19 21:00');

ALTER TABLE users AUTO_INCREMENT = 0;


