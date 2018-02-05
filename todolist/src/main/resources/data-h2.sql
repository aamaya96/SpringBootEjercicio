INSERT INTO api_user(user_name, first_name, last_name) VALUES ('worker1', 'Juan', 'Flores');
INSERT INTO api_user(user_name, first_name, last_name) VALUES ('programmer504', 'Carlos', 'Medina');
INSERT INTO api_user(user_name, first_name, last_name) VALUES ('counter123', 'Melissa', 'Corrales');

INSERT INTO category(category_name) VALUES ('work');
INSERT INTO category(category_name) VALUES ('private');

INSERT INTO to_do(priority, title, description, user_name, category_name)
VALUES (1, 'dinner', 'dinner at marthas house', 'worker1', 'private');
INSERT INTO to_do(priority, title, description, user_name, category_name)
VALUES (2, 'dinner', 'dinner with costumer', 'worker1', 'work');
INSERT INTO to_do(priority, title, description, user_name, category_name)
VALUES (1, 'report', 'sales report', 'worker1', 'work');
INSERT INTO to_do(priority, title, description, user_name, category_name)
VALUES (1, 'test', '', 'programmer504', 'work');
INSERT INTO to_do(priority, title, description,  user_name, category_name)
VALUES (2, 'debug', '', 'programmer504', 'work');
