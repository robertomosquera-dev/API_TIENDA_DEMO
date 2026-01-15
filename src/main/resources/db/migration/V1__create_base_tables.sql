-- ============================
-- DOMAIN TABLES
-- ============================

CREATE TABLE category (
    category_id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    state_delete ENUM('ACTIVE','INACTIVE') DEFAULT 'ACTIVE'
);

CREATE TABLE customer (
    customer_id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    state_delete ENUM('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
    user_id BINARY(16)
);

CREATE TABLE product (
    product_id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price DECIMAL(10,2),
    stock INT,
    category_id BINARY(16),
    state_delete ENUM('ACTIVE','INACTIVE') DEFAULT 'ACTIVE',
    CONSTRAINT FK_product_category
        FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE purchase_order (
    purchase_order_id BINARY(16) NOT NULL PRIMARY KEY,
    date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2),
    state_order ENUM('CANCELLED','COMPLETED','PENDING') DEFAULT 'PENDING',
    customer_id BINARY(16),
    CONSTRAINT FK_purchase_order_customer
        FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE order_item (
    order_item_id BINARY(16) NOT NULL PRIMARY KEY,
    purchase_order_id BINARY(16) NOT NULL,
    product_id BINARY(16),
    quantity INT,
    total_item DECIMAL(10,2),
    CONSTRAINT FK_order_item_order
        FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(purchase_order_id),
    CONSTRAINT FK_order_item_product
        FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- ============================
-- SECURITY TABLES
-- ============================

CREATE TABLE users (
    user_id BINARY(16) NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    is_enabled BOOLEAN DEFAULT TRUE,
    account_non_expired BOOLEAN DEFAULT TRUE,
    credentials_non_expired BOOLEAN DEFAULT TRUE,
    account_non_locked BOOLEAN DEFAULT TRUE
);

CREATE TABLE roles (
    role_id BINARY(16) NOT NULL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE permission (
    permission_id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_user_roles_user
        FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_roles_role
        FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE role_permissions (
    role_id BINARY(16) NOT NULL,
    permission_id BINARY(16) NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT FK_role_permissions_role
        FOREIGN KEY (role_id) REFERENCES roles(role_id),
    CONSTRAINT FK_role_permissions_permission
        FOREIGN KEY (permission_id) REFERENCES permission(permission_id)
);

-- ============================
-- FOREIGN KEY CUSTOMER → USER
-- ============================

ALTER TABLE customer
    ADD CONSTRAINT FK_customer_user
        FOREIGN KEY (user_id) REFERENCES users(user_id);
