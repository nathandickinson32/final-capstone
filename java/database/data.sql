BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

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

INSERT INTO recipe_users(recipe_id, user_id)
VALUES (1,1), (2,1);

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
