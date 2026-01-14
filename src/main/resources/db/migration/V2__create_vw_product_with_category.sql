CREATE VIEW vw_product_with_category AS
SELECT
    p.product_id,
    p.name AS product,
    p.stock,
    p.price,
    c.name AS category
FROM product p
INNER JOIN category c
ON p.category_id = c.category_id;