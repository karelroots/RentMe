INSERT INTO ROLE (role_id, role)
VALUES (1, 'ADMIN');

INSERT INTO ROLE (role_id, role)
VALUES (2, 'KASUTAJA');

INSERT INTO USERS (user_id, username, email, password, name, last_name, active)
VALUES (999, 'test', 'test@test.ee', '$2y$12$Tm5/fkXIPjqmml0qPokjieGmiIaenDZkaR7/aoygO4uQCZxqBmUnu', 'Test', 'Test', 1);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (999, 1);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (999, 2);