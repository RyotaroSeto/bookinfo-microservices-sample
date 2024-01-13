CREATE DATABASE ratings;

CREATE TABLE ratings.ratings (
    id INT AUTO_INCREMENT,
    product_id INT,
    reviewer TEXT,
    stars INT,
    PRIMARY KEY (id)
);

INSERT INTO ratings.ratings (
    product_id,
    reviewer,
    stars
) values (
    0,
    'Alice',
    5
);

INSERT INTO ratings.ratings (
    product_id,
    reviewer,
    stars
) values (
    0,
    'Bob',
    4
);