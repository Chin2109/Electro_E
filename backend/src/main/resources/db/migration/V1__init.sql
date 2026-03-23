-- =========================
-- 1) roles
-- =========================
CREATE TABLE roles (
    role_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- =========================
-- 2) users
-- =========================
CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL UNIQUE,
    gender BIGINT NOT NULL DEFAULT 0,  -- 0: Unknown, 1: Nam, 2: Nữ
    status BIGINT NOT NULL DEFAULT 1   -- 1: Active, 0: Inactive, 2: Banned
);

-- =========================
-- 3) user_roles (many-to-many)
-- =========================
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);
CREATE INDEX idx_user_roles_user ON user_roles(user_id);
CREATE INDEX idx_user_roles_role ON user_roles(role_id);

-- =========================
-- 4) categories
-- =========================
CREATE TABLE categories (
    category_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    status BIGINT NOT NULL DEFAULT 1  -- 1: Available, 2: Disable
);

-- =========================
-- 5) brands
-- =========================
CREATE TABLE brands (
    brand_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    url_image VARCHAR(255),
    description TEXT
);

-- =========================
-- 6) products
-- =========================
CREATE TABLE products (
    product_id BIGSERIAL PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    short_description VARCHAR(255),
    description TEXT,
    article TEXT,
    status BIGINT NOT NULL DEFAULT 1,  -- 1: Active, 0: Inactive
    CONSTRAINT fk_products_brand FOREIGN KEY (brand_id) REFERENCES brands(brand_id) ON DELETE RESTRICT
);
CREATE INDEX idx_products_brand ON products(brand_id);
CREATE INDEX idx_products_name ON products(name);

-- =========================
-- 7) product_category (many-to-many product <> category)
-- =========================
CREATE TABLE product_category (
    product_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk_pc_product FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE,
    CONSTRAINT fk_pc_category FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);
CREATE INDEX idx_pc_product ON product_category(product_id);
CREATE INDEX idx_pc_category ON product_category(category_id);

-- =========================
-- 8) spec_attribute (attributes per category)
-- =========================
CREATE TABLE spec_attribute (
    attribute_id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    unit VARCHAR(20),
    CONSTRAINT fk_specattr_category FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);
CREATE INDEX idx_spec_attribute_category ON spec_attribute(category_id);

-- =========================
-- 9) product_spec_attribute (value of attribute for product)
-- =========================
CREATE TABLE product_spec_attribute (
    product_id BIGINT NOT NULL,
    attribute_id BIGINT NOT NULL,
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY (product_id, attribute_id),
    CONSTRAINT fk_psa_product FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE,
    CONSTRAINT fk_psa_attribute FOREIGN KEY (attribute_id) REFERENCES spec_attribute(attribute_id) ON DELETE CASCADE
);
CREATE INDEX idx_psa_product ON product_spec_attribute(product_id);
CREATE INDEX idx_psa_attribute ON product_spec_attribute(attribute_id);

-- =========================
-- 10) variant_attribute (defines variant axes per product, e.g., COLOR, SIZE)
-- =========================
CREATE TABLE variant_attribute (
    attribute_id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT fk_varattr_product FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE,
    CONSTRAINT uq_varattr_product_code UNIQUE (product_id, code)
);
CREATE INDEX idx_varattr_product ON variant_attribute(product_id);

-- =========================
-- 11) variant_option (values for each variant_attribute)
-- =========================
CREATE TABLE variant_option (
    option_id BIGSERIAL PRIMARY KEY,
    attribute_id BIGINT NOT NULL,
    value VARCHAR(100) NOT NULL,
    CONSTRAINT fk_variantoption_attribute FOREIGN KEY (attribute_id) REFERENCES variant_attribute(attribute_id) ON DELETE CASCADE
);
CREATE INDEX idx_variant_option_attribute ON variant_option(attribute_id);

-- =========================
-- 12) product_variant (sellable variant)
-- =========================
CREATE TABLE product_variant (
    variant_id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    status BIGINT NOT NULL DEFAULT 1,        -- 1: Active, 2: Hidden, 3: Out_of_stock
    buying_price NUMERIC NOT NULL DEFAULT 0,
    selling_price NUMERIC NOT NULL DEFAULT 0,
    stock_quantity BIGINT NOT NULL DEFAULT 0,
    sold_quantity BIGINT NOT NULL DEFAULT 0,
    sku VARCHAR(255) UNIQUE,
    CONSTRAINT fk_variant_product FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);
CREATE INDEX idx_product_variant_product ON product_variant(product_id);
CREATE INDEX idx_product_variant_sku ON product_variant(sku);

-- =========================
-- 13) product_variant_option (mapping variant <-> option)
-- =========================
CREATE TABLE product_variant_option (
    variant_id BIGINT NOT NULL,
    option_id BIGINT NOT NULL,
    PRIMARY KEY (variant_id, option_id),
    CONSTRAINT fk_pvo_variant FOREIGN KEY (variant_id) REFERENCES product_variant(variant_id) ON DELETE CASCADE,
    CONSTRAINT fk_pvo_option FOREIGN KEY (option_id) REFERENCES variant_option(option_id) ON DELETE CASCADE
);

-- =========================
-- 14) images (for variant)
-- =========================
CREATE TABLE images (
    image_id BIGSERIAL PRIMARY KEY,
    variant_id BIGINT NOT NULL,
    path VARCHAR(255) NOT NULL,
    is_thumbnail BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_image_variant FOREIGN KEY (variant_id) REFERENCES product_variant(variant_id) ON DELETE CASCADE
);
CREATE INDEX idx_images_variant ON images(variant_id);

-- =========================
-- 15) warehouse
-- =========================
CREATE TABLE warehouse (
    warehouse_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50),
    phone VARCHAR(50),
    address VARCHAR(255),
    status BIGINT NOT NULL DEFAULT 1  -- 1: Active, 2: Inactive
);

-- =========================
-- 16) supplier
-- =========================
CREATE TABLE supplier (
    supplier_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    company_name VARCHAR(150),
    tax_code VARCHAR(50) NOT NULL UNIQUE,
    fax VARCHAR(50),
    contact_email VARCHAR(100),
    contact_phone VARCHAR(20),
    status BIGINT NOT NULL DEFAULT 1,
    description TEXT
);

-- =========================
-- 17) warranty_policy
-- =========================
CREATE TABLE warranty_policy (
    warrant_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    duration_months BIGINT,
    description TEXT
);

-- =========================
-- 18) warranty_policy_product (mapping)
-- =========================
CREATE TABLE warranty_policy_product (
    warranty_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price NUMERIC NOT NULL DEFAULT 0,
    PRIMARY KEY (warranty_id, product_id),
    CONSTRAINT fk_wpp_warranty FOREIGN KEY (warranty_id) REFERENCES warranty_policy(warrant_id) ON DELETE CASCADE,
    CONSTRAINT fk_wpp_product FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

-- =========================
-- 19) product_item (individual physical unit, may reference order later)
-- =========================
CREATE TABLE product_item (
    id BIGSERIAL PRIMARY KEY,
    provar_id BIGINT NOT NULL,   -- product_variant.variant_id
    warehouse_id BIGINT NOT NULL,
    order_id BIGINT,             -- added as nullable; FK to orders to be added later
    status BIGINT NOT NULL DEFAULT 1,  -- 1: In_Stock, 2: Reserved, 3: Sold, 4: Repair, 5: Defective, 6: Demo
    serial_number VARCHAR(255) UNIQUE,
    CONSTRAINT fk_productitem_variant FOREIGN KEY (provar_id) REFERENCES product_variant(variant_id) ON DELETE RESTRICT,
    CONSTRAINT fk_productitem_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id) ON DELETE RESTRICT
);
CREATE INDEX idx_product_item_variant ON product_item(provar_id);
CREATE INDEX idx_product_item_warehouse ON product_item(warehouse_id);
CREATE INDEX idx_product_item_serial ON product_item(serial_number);

-- =========================
-- 20) stock_in
-- =========================
CREATE TABLE stock_in (
    stockin_id BIGSERIAL PRIMARY KEY,
    supplier_id BIGINT NOT NULL,
    warehouse_id BIGINT NOT NULL,
    staff_id BIGINT NOT NULL,   -- user who created
    total_amount NUMERIC,
    status BIGINT NOT NULL DEFAULT 1, -- 1: Pending, 2: Checking, 3: Completed
    import_date TIMESTAMP NOT NULL DEFAULT now(),
    note TEXT,
    CONSTRAINT fk_stockin_supplier FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON DELETE RESTRICT,
    CONSTRAINT fk_stockin_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id) ON DELETE RESTRICT,
    CONSTRAINT fk_stockin_staff FOREIGN KEY (staff_id) REFERENCES users(user_id) ON DELETE RESTRICT
);
CREATE INDEX idx_stockin_supplier ON stock_in(supplier_id);
CREATE INDEX idx_stockin_warehouse ON stock_in(warehouse_id);

-- =========================
-- 21) stock_in_item
-- =========================
CREATE TABLE stock_in_item (
    stockin_item_id BIGSERIAL PRIMARY KEY,
    stockin_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,   -- product_item.id
    import_price NUMERIC NOT NULL DEFAULT 0,
    CONSTRAINT fk_sii_stockin FOREIGN KEY (stockin_id) REFERENCES stock_in(stockin_id) ON DELETE CASCADE,
    CONSTRAINT fk_sii_item FOREIGN KEY (item_id) REFERENCES product_item(id) ON DELETE RESTRICT
);
CREATE INDEX idx_stockinitem_stockin ON stock_in_item(stockin_id);
CREATE INDEX idx_stockinitem_item ON stock_in_item(item_id);

-- =========================
-- 22) stock_out (order_id FK added later)
-- =========================
CREATE TABLE stock_out (
    stockout_id BIGSERIAL PRIMARY KEY,
    warehouse_id BIGINT NOT NULL,
    order_id BIGINT,  -- nullable, FK to orders added later
    staff_id BIGINT NOT NULL,
    export_date TIMESTAMP NOT NULL DEFAULT now(),
    type_of_export BIGINT NOT NULL DEFAULT 1,
    total_quantity BIGINT,
    note TEXT,
    status BIGINT NOT NULL DEFAULT 1, -- 1: Pending, 2: Completed
    CONSTRAINT fk_stockout_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id) ON DELETE RESTRICT,
    CONSTRAINT fk_stockout_staff FOREIGN KEY (staff_id) REFERENCES users(user_id) ON DELETE RESTRICT
);
CREATE INDEX idx_stockout_warehouse ON stock_out(warehouse_id);
CREATE INDEX idx_stockout_staff ON stock_out(staff_id);

-- =========================
-- 23) stock_out_item
-- =========================
CREATE TABLE stock_out_item (
    stockout_item_id BIGSERIAL PRIMARY KEY,
    stockout_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    CONSTRAINT fk_soi_stockout FOREIGN KEY (stockout_id) REFERENCES stock_out(stockout_id) ON DELETE CASCADE,
    CONSTRAINT fk_soi_item FOREIGN KEY (item_id) REFERENCES product_item(id) ON DELETE RESTRICT
);
CREATE INDEX idx_stockoutitem_stockout ON stock_out_item(stockout_id);
CREATE INDEX idx_stockoutitem_item ON stock_out_item(item_id);

-- =========================
-- 24) inventory_transaction
-- =========================
CREATE TABLE inventory_transaction (
    transaction_id BIGSERIAL PRIMARY KEY,
    variant_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL DEFAULT 0,
    ref_id BIGINT,
    ref_type VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_invtrans_variant FOREIGN KEY (variant_id) REFERENCES product_variant(variant_id) ON DELETE RESTRICT
);
CREATE INDEX idx_invtrans_variant ON inventory_transaction(variant_id);
CREATE INDEX idx_invtrans_ref ON inventory_transaction(ref_type, ref_id);

-- =========================
-- 25) cart
-- =========================
CREATE TABLE cart (
    cart_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
CREATE INDEX idx_cart_user ON cart(user_id);

-- =========================
-- 26) cart_item
-- =========================
CREATE TABLE cart_item (
    cart_item_id BIGSERIAL PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    variant_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL DEFAULT 1,
    CONSTRAINT fk_cartitem_cart FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,
    CONSTRAINT fk_cartitem_variant FOREIGN KEY (variant_id) REFERENCES product_variant(variant_id) ON DELETE RESTRICT
);
CREATE INDEX idx_cart_item_cart ON cart_item(cart_id);
CREATE INDEX idx_cart_item_variant ON cart_item(variant_id);

-- =========================
-- 27) promotions
-- =========================
CREATE TABLE promotions (
    promo_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type BIGINT NOT NULL DEFAULT 1, -- 1: direct, 2: bundle, 3: order-level
    discount_value NUMERIC NOT NULL DEFAULT 0,
    is_percent BOOLEAN NOT NULL DEFAULT TRUE,
    start_date TIMESTAMP NOT NULL DEFAULT now(),
    end_date TIMESTAMP,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    apply_condition NUMERIC,
    note TEXT,
    max_usage BIGINT,
    current_usage BIGINT NOT NULL DEFAULT 0
);

-- =========================
-- 28) promotions_productvariant (mapping)
-- =========================
CREATE TABLE promotions_productvariant (
    promo_id BIGINT NOT NULL,
    variant_id BIGINT NOT NULL,
    PRIMARY KEY (promo_id, variant_id),
    CONSTRAINT fk_ppv_promo FOREIGN KEY (promo_id) REFERENCES promotions(promo_id) ON DELETE CASCADE,
    CONSTRAINT fk_ppv_variant FOREIGN KEY (variant_id) REFERENCES product_variant(variant_id) ON DELETE CASCADE
);

-- =========================
-- 29) preorder
-- =========================
CREATE TABLE preorder (
    preorder_id BIGSERIAL PRIMARY KEY,
    variant_id BIGINT NOT NULL,
    user_id BIGINT,
    fullname VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    status BIGINT NOT NULL DEFAULT 1, -- 1: waiting, 2: notified, 3: created order, 4: cancelled
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    note TEXT,
    CONSTRAINT fk_preorder_variant FOREIGN KEY (variant_id) REFERENCES product_variant(variant_id) ON DELETE CASCADE,
    CONSTRAINT fk_preorder_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL
);
CREATE INDEX idx_preorder_variant ON preorder(variant_id);
CREATE INDEX idx_preorder_user ON preorder(user_id);

-- =========================
-- 30) orders (stockout_id FK added later)
-- =========================
CREATE TABLE orders (
    order_id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    stockout_id BIGINT,   -- FK to stock_out added later to avoid circular FK at create time
    warehouse_id BIGINT,
    shipper_id BIGINT,
    created_at_datetime TIMESTAMP NOT NULL DEFAULT now(),
    completed_at TIMESTAMP,
    status_order BIGINT NOT NULL DEFAULT 0,
    delivery_method BIGINT,
    to_phone VARCHAR(20),
    to_name VARCHAR(100),
    to_address VARCHAR(255),
    to_ward VARCHAR(100),
    to_district VARCHAR(100),
    to_province VARCHAR(100),
    total_amount NUMERIC NOT NULL DEFAULT 0,
    shipping_cost NUMERIC NOT NULL DEFAULT 0,
    total_pay NUMERIC NOT NULL DEFAULT 0,
    payment_method BIGINT NOT NULL DEFAULT 0,
    payment_status BIGINT NOT NULL DEFAULT 0,
    note TEXT,
    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE RESTRICT,
    CONSTRAINT fk_orders_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id) ON DELETE SET NULL,
    CONSTRAINT fk_orders_shipper FOREIGN KEY (shipper_id) REFERENCES users(user_id) ON DELETE SET NULL
);
CREATE INDEX idx_orders_user ON orders(user_id);
CREATE INDEX idx_orders_code ON orders(code);

-- =========================
-- 31) order_item
-- =========================
CREATE TABLE order_item (
    order_item_id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,  -- product_item.id
    promo_id BIGINT,
    unit_price NUMERIC NOT NULL DEFAULT 0,
    cost_price NUMERIC NOT NULL DEFAULT 0,
    discount_amount NUMERIC,
    total NUMERIC,
    CONSTRAINT fk_orderitem_order FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT fk_orderitem_item FOREIGN KEY (item_id) REFERENCES product_item(id) ON DELETE RESTRICT,
    CONSTRAINT fk_orderitem_promo FOREIGN KEY (promo_id) REFERENCES promotions(promo_id) ON DELETE SET NULL
);
CREATE INDEX idx_orderitem_order ON order_item(order_id);
CREATE INDEX idx_orderitem_item ON order_item(item_id);

-- =========================
-- 32) warranty_ticket
-- =========================
CREATE TABLE warranty_ticket (
    ticket_id BIGSERIAL PRIMARY KEY,
    productitem_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    warehouse_id BIGINT NOT NULL,
    issue_description TEXT NOT NULL,
    status BIGINT,
    received_date TIMESTAMP,
    expected_return TIMESTAMP,
    return_date TIMESTAMP,
    technician_note TEXT,
    CONSTRAINT fk_wt_productitem FOREIGN KEY (productitem_id) REFERENCES product_item(id) ON DELETE RESTRICT,
    CONSTRAINT fk_wt_customer FOREIGN KEY (customer_id) REFERENCES users(user_id) ON DELETE RESTRICT,
    CONSTRAINT fk_wt_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id) ON DELETE RESTRICT
);
CREATE INDEX idx_wt_productitem ON warranty_ticket(productitem_id);

-- =========================
-- 33) Add circular/late FKs
-- =========================
ALTER TABLE stock_out
    ADD CONSTRAINT fk_stockout_order FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE SET NULL;

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_stockout FOREIGN KEY (stockout_id) REFERENCES stock_out(stockout_id) ON DELETE SET NULL;

ALTER TABLE product_item
    ADD CONSTRAINT fk_productitem_order FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE SET NULL;

-- =========================
-- 34) Additional helpful indexes
-- =========================
CREATE INDEX IF NOT EXISTS idx_products_name ON products(name);
CREATE INDEX IF NOT EXISTS idx_product_variant_productid ON product_variant(product_id);
CREATE INDEX IF NOT EXISTS idx_product_item_serial ON product_item(serial_number);