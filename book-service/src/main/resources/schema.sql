DROP TABLE IF EXISTS book;

CREATE TABLE book(
    book_id         INT PRIMARY KEY NOT NULL,
    name            VARCHAR (100) NOT NULL ,
    price           INT NOT NULL,
    category_id     INT
);


INSERT INTO book(book_id, name, price, category_id)
VALUE (1, 'Harry Potter', 100, 1);
