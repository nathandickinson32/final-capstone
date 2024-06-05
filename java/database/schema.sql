BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS recipe_instructions;
DROP TABLE IF EXISTS recipe_categories;
DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS categories;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE categories (
category_id SERIAL,
category_type varchar NOT NULL,
category_name varchar NOT NULL,
CONSTRAINT PK_category_id PRIMARY KEY(category_id)
);

CREATE TABLE recipe (
recipe_id SERIAL,
recipe_name varchar NOT NULL,
description varchar NOT NULL,
author_id int NOT NULL,
CONSTRAINT PK_recipe_id PRIMARY KEY(recipe_id)

);



CREATE TABLE recipe_categories (
recipe_id int NOT NULL,
category_id int NOT NULL,
CONSTRAINT FK_recipe_id FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id),
CONSTRAINT FK_category_id FOREIGN KEY(category_id) REFERENCES categories(category_id)
);

CREATE TABLE ingredients (
ingredient_id SERIAL,
ingredient_name varchar NOT NULL,
CONSTRAINT PK_ingredients PRIMARY KEY(ingredient_id)
);

CREATE TABLE recipe_ingredients (
recipe_id int NOT NULL,
ingredient_id int NOT NULL,
--CONSTRAINT PK_recipe_ingredient PRIMARY KEY (recipe_id, ingredient_id),
CONSTRAINT FK_recipe_id FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id),
CONSTRAINT FK_ingredient_id FOREIGN KEY(ingredient_id) REFERENCES ingredients(ingredient_id)
);

CREATE TABLE recipe_instructions(
instructions_id SERIAL,
step int NOT NULL,
instruction varchar NOT NULL,
recipe_id int NOT NULL,
CONSTRAINT PK_instructions_id PRIMARY KEY(instructions_id),
CONSTRAINT FK_recipe_id FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id)
);

INSERT INTO categories(category_type, category_name)
VALUES
('Time', 'Breakfast'),
('Time', 'Lunch'),
('Time', 'Dinner'),
('Diet', 'High Protein'),
('Diet', 'Low-Carb'),
('Diet', 'Vegetarian/Vegan'),
('Flavor', 'Asian'),
('Flavor', 'Italian'),
('Flavor', 'Mexican'),
('Flavor', 'Middle-Eastern');

INSERT INTO recipe(recipe_name, description, author_id)
VALUES
('Stir-Fry', 'Stir & Fry', 1),
('Fries', 'fry em', 2);

INSERT INTO recipe_categories(recipe_id, category_id)
VALUES
(1,1),
(2,2);




INSERT INTO ingredients(ingredient_name)
VALUES

('Carrots'),
('Potatoes'),
('Onions'),
('Garlic'),
('Bell peppers '),
('Tomatoes'),
('Spinach'),
('Kale'),
('Broccoli'),
('Cauliflower'),
('Zucchini'),
('Eggplant'),
('Mushrooms'),
('Cucumbers'),
('Lettuce'),
('Avocados'),
('Corn'),
('Apples'),
('Bananas'),
('Oranges'),
('Strawberries'),
('Blueberries'),
('Grapes'),
('Lemons'),
('Limes'),
('Pineapples'),
('Mangoes'),
('Peaches'),
('Cherries'),
('Watermelon'),
('Kiwi'),
('Rice'),
('Quinoa'),
('Oats'),
('Barley'),
('Wheat flour'),
('Cornmeal'),
('Couscous'),
('Bulgur'),
('Chickpeas'),
('Lentils'),
('Beans'),
('Peas'),
('Milk'),
('Cheese'),
('Yogurt'),
('Butter'),
('Cream'),
('Eggs'),
('Chicken'),
('Beef'),
('Pork'),
('Lamb'),
('Turkey'),
('Seafood'),
('Salmon'),
('Tuna'),
('Shrimp'),
('Cod'),
('Scallops'),
('Crab'),
('Lobster'),
('Salt'),
('Black pepper'),
('Cumin'),
('Paprika'),
('Turmeric'),
('Cinnamon'),
('Nutmeg'),
('Oregano'),
('Basil'),
('Thyme'),
('Rosemary'),
('Parsley'),
('Cilantro'),
('Olive oil'),
('Vegetable oil'),
('Coconut oil'),
('Butter'),
('Ghee'),
('Soy sauce'),
('Ketchup'),
('Mustard'),
('Mayonnaise'),
('Hot sauce'),
('BBQ sauce'),
('Vinegar'),
('Honey'),
('Maple syrup'),
('Flour'),
('Sugar'),
('Baking powder'),
('Baking soda'),
('Yeast'),
('Vanilla extract'),
('Cocoa powder'),
('Chocolate chips'),
('Almonds'),
('Walnuts'),
('Cashews'),
('Peanuts'),
('Sunflower seeds'),
('Chia seeds'),
('Flax seeds'),
('Pumpkin seeds'),
('Beverages'),
('Coffee'),
('Tea'),
('Juice'),
('Soda'),
('Water'),
('Wine'),
('Beer'),
('Miscellaneous'),
('Bread'),
('Pasta'),
('Canned goods (tomatoes, beans, corn)'),
('Broth (chicken, beef, vegetable)'),
('Tofu'),
('Miso paste');

INSERT INTO recipe_ingredients(recipe_id, ingredient_id)
VALUES
(1,1),
(2,2),
(2,5);

INSERT INTO recipe_instructions(step, instruction, recipe_id)
VALUES
(1,'get fries', 2),
(2,'fyr em', 2),
(3,'eat them while they are hot!', 2);



COMMIT TRANSACTION;