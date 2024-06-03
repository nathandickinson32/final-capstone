BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
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
category_id int NOT NULL,
CONSTRAINT PK_recipe_id PRIMARY KEY(recipe_id),
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
CONSTRAINT FK_recipe_id FOREIGN KEY(recipe_id) REFERENCES recipe(recipe_id),
CONSTRAINT FK_ingredient_id FOREIGN KEY(ingredient_id) REFERENCES ingredients(ingredient_id)

);

COMMIT TRANSACTION;