BEGIN TRANSACTION;

DROP TABLE IF EXISTS ingredients_measurements;
DROP TABLE IF EXISTS ingredients_users;
DROP TABLE IF EXISTS recipe_users;
DROP TABLE IF EXISTS recipe_instructions;
DROP TABLE IF EXISTS recipe_categories;
DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS measurements;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;


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
ingredient_name varchar NOT NULL UNIQUE,
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

CREATE TABLE recipe_users(
recipe_id int NOT NULL,
user_id int NOT NULL,
CONSTRAINT FK_recipe_id FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id),
CONSTRAINT FK_user_id FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE measurements (
measurement_type varchar NOT NULL UNIQUE
);

CREATE TABLE ingredients_measurements (
ingredient_id int NOT NULL,
numerator int NOT NULL,
denominator int,
measurement_type varchar NOT NULL,
CONSTRAINT FK_ingredient_id FOREIGN KEY(ingredient_id) REFERENCES ingredients(ingredient_id),
CONSTRAINT FK_measurement_type FOREIGN KEY(measurement_type) REFERENCES measurements(measurement_type)
);

CREATE TABLE ingredients_users (
ingredient_id int NOT NULL,
user_id int NOT NULL,
quantity int NOT NULL
);

COMMIT TRANSACTION;