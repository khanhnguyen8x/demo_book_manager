DROP TABLE IF EXISTS category;

CREATE TABLE category(
                         category_id     SERIAL PRIMARY KEY,
                         name            VARCHAR (100) NOT NULL ,
                         description     VARCHAR(100) NOT NULL
);


INSERT INTO category(category_id, name, description)
VALUES (1, 'IT', 'Information Technology');

INSERT INTO category(category_id, name, description)
VALUES (2, 'Music', 'Music');

INSERT INTO category(category_id, name, description)
VALUES (3, 'Art', 'Art in Life');
