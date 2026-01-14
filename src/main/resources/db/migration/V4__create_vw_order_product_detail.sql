CREATE VIEW vw_order_product_detail AS
SELECT
    po.purchase_order_id,
    po.state_order,
    oi.quantity,
    oi.total_item,
    p.name AS product
FROM purchase_order po
JOIN order_item oi
ON oi.purchase_order_id = po.purchase_order_id
JOIN product p
ON p.product_id = oi.product_id;