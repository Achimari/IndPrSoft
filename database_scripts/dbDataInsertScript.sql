INSERT INTO users(name, surname, email, password)
VALUES ('Admin', 'Admin', 'admin@admin.lv', 'admin');


INSERT INTO  dish(name, description, type, weight, price)
VALUES ('PIZZA 4FORMAGGIO', 'pizza with 4 cheeses', 'PIZZA', 350, 7.99);

INSERT INTO  dish(name, description, type, weight, price)
VALUES ('PIZZA HAWAII', 'pizza with pineapple and ham', 'PIZZA', 400, 9.99);

INSERT INTO  dish(name, description, type, weight, price)
VALUES ('PIZZA MARGHERITA', 'vegetarian pizza with cheese', 'PIZZA', 320, 6.99);

INSERT INTO  dish(name, description, type, weight, price)
VALUES ('PASTA CARBONARA', 'pasta with egg and pepper', 'PASTA', 300, 10.99);



INSERT INTO product(name, quantity, price, expiryDate)
VALUES('flour', 10, 0.5, '2021-12-12');

INSERT INTO product(name, quantity, price, expiryDate)
VALUES('olive oil', 20, 2.25, '2022-06-12');

INSERT INTO product(name, quantity, price, expiryDate)
VALUES('tomatoes', 5, 0.9, '2021-06-01');

INSERT INTO product(name, quantity, price, expiryDate)
VALUES('ham', 3, 4.24, '2021-07-01');

INSERT INTO product(name, quantity, price, expiryDate)
VALUES('cheese', 10, 5.00, '2021-07-01');

INSERT INTO product(name, quantity, price, expiryDate)
VALUES('pineapple', 10, 0.5, '2021-07-01');

INSERT INTO product(name, quantity, price, expiryDate)
VALUES('egg', 10, 0.5, '2021-07-01');


INSERT INTO ingredient(dish_id, name, quantity)
VALUES(1, 'cheddar', 30),
(1, 'mozzarella', 30),
(1, 'dutch cheese classical', 30),
(1, 'parmesan', 30),
(1, 'flour', 100);

INSERT INTO ingredient(dish_id, name, quantity)
VALUES(2, 'pineapple', 50),
(2, 'mozzarella', 40),
(2, 'oil', 10),
(2, 'ham', 50),
(2, 'flour', 100);

INSERT INTO ingredient(dish_id, name, quantity)
VALUES(3, 'tomato sauce', 50),
(3, 'mozzarella', 40),
(3, 'oil', 10),
(3, 'flour', 100);

INSERT INTO ingredient(dish_id, name, quantity)
VALUES(4, 'pasta spaghetti', 80),
(4, 'egg', 55),
(4, 'oil', 10),
(4, 'pepper', 4);