INSERT INTO catalogue (name, price, short_description) VALUES ('Apples', 2.99, 'Fresh, juicy apples from the local orchard.');
INSERT INTO catalogue (name, price, short_description) VALUES ('Bread', 3.49, 'Artisan bread baked daily with the finest ingredients.');
INSERT INTO catalogue (name, price, short_description) VALUES ('Milk', 1.99, 'Organic milk from grass-fed cows.');
INSERT INTO catalogue (name, price, short_description) VALUES ('Eggs', 2.49, 'Farm-fresh eggs from free-range chickens.');
INSERT INTO catalogue (name, price, short_description) VALUES ('Cheese', 4.99, 'Imported cheese aged to perfection.');

INSERT INTO cart (catalogue_id, quantity, created_at, updated_at) VALUES (1, 2, NOW(), NOW());
INSERT INTO cart (catalogue_id, quantity, created_at, updated_at) VALUES (2, 1, NOW(), NOW());
INSERT INTO cart (catalogue_id, quantity, created_at, updated_at) VALUES (3, 3, NOW(), NOW());
