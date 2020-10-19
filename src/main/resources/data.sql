INSERT INTO ROLE (role_id, role)
VALUES (1, 'ADMIN');

INSERT INTO ROLE (role_id, role)
VALUES (2, 'KASUTAJA');

INSERT INTO USERS (user_id, username, email, password, name, last_name, active)
VALUES (999, 'test', 'test@test.ee', '$2y$12$Tm5/fkXIPjqmml0qPokjieGmiIaenDZkaR7/aoygO4uQCZxqBmUnu', 'Test', 'Test', 1);

INSERT INTO USERS (user_id, username, email, password, name, last_name, active)
VALUES (9999, 'test2', 't@t.ee', '$2y$12$Tm5/fkXIPjqmml0qPokjieGmiIaenDZkaR7/aoygO4uQCZxqBmUnu', 'Test2', 'Test2',
 1);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (999, 1);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (999, 2);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (9999, 2);

CREATE VIEW USER_AUTH AS
SELECT user_id FROM USERS;

CREATE VIEW USER_BROWSER AS
SELECT browser FROM USER_LOG;

CREATE VIEW USER_OS AS
SELECT os FROM USER_LOG;

CREATE VIEW LANDING_PAGES AS
SELECT landing_page FROM USER_LOG;