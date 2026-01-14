CREATE VIEW vw_customer_with_orders AS
SELECT
    po.purchase_order_id,
    c.name AS customer,
    po.date_time,
    po.state_order,
    po.total
FROM customer c
INNER JOIN purchase_order po
ON c.customer_id = po.customer_id;