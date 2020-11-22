INSERT INTO ROLE (role_id, role)
VALUES (1, 'ADMIN');

INSERT INTO ROLE (role_id, role)
VALUES (2, 'KASUTAJA');

INSERT INTO USERS (user_id, username, email, password, name, last_name, active)
VALUES (999, 'test', 'test@test.ee', '$2y$12$py61EKFyvdPhzPNbMw/Um.dcEEkS9YhaaGT6mP194PEomkVRsu9em', 'Test', 'Test', 1);

INSERT INTO USERS (user_id, username, email, password, name, last_name, active)
VALUES (9999, 'test99', 't@t.ee', '$2y$12$Tm5/fkXIPjqmml0qPokjieGmiIaenDZkaR7/aoygO4uQCZxqBmUnu', 'Test2', 'Test2',
 1);

INSERT INTO USERS (user_id, username, email, password, name, last_name, active)
VALUES (123, 'johndoe', 'JohnDoe99@gmail.com', '$2y$12$YqueAE9Tt8tGPxYlMJqpbuY.RZ6qt1BQAZbjspUwODNFNhD.sQGgq ', 'Test',
'Test', 1);

INSERT INTO USERS (user_id, username, email, password, name, last_name, active)
VALUES (222, 'maryjane', 'MaryJane@gmail.com', '$2y$12$0fFype0N4SslyIDCb0yX3Ov20tzAD3rBaF26p1p2NCwCxZg2Nqq66 ', 'Test',
'Test', 1);


INSERT INTO USER_ROLE (user_id, role_id)
VALUES (999, 1);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (999, 2);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (9999, 2);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (123, 2);

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (222, 2);

CREATE VIEW USER_AUTH AS
SELECT user_id FROM USERS;

CREATE VIEW USER_BROWSER AS
SELECT browser FROM USER_LOG;

CREATE VIEW USER_OS AS
SELECT os FROM USER_LOG;

CREATE VIEW LANDING_PAGES AS
SELECT landing_page FROM USER_LOG;

INSERT INTO OFFERS (offer_id, user_id, item_name, item_desc, price, location, time, rent_period)
VALUES (1, 999, 'Banana', 'Just banana', 2, 'Tartu', '12.12.2012', '1');

INSERT INTO OFFERS (offer_id, user_id, item_name, item_desc, price, location, time, rent_period)
VALUES (2, 9999, 'Hammer', 'Just hammer', 3, 'Tartu', '12.12.2012', '1');

INSERT INTO WISHES (wish_id, user_id, item_name, item_desc, location, time, rent_period)
VALUES (1, 999, 'Vacuum', 'The best vacuum money can buy', 'Tartu', '8.2.2010', '2');

INSERT INTO WISHES (wish_id, user_id, item_name, item_desc, location, time, rent_period)
VALUES (2, 9999, 'Nails', 'A bunch of nails', 'Tartu', '10.11.2014', '99');

INSERT INTO WISHES (wish_id, user_id, item_name, item_desc, location, time, rent_period)
VALUES (3, 9999, 'Cheap vacuum', 'For free please?', 'Tartu', '8.12.2020', '2');