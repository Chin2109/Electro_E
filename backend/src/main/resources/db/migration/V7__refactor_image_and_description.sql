-- =========================
-- V7__refactor_image_and_description.sql
-- =========================

-- =========================
-- 1. TRUNCATE DATA (dev only)
-- =========================
TRUNCATE TABLE
    images,
    product_variant_option,
    product_variant,
    variant_attribute,
    product_category,
    products
CASCADE;

-- =========================
-- 2. REFACTOR images (variant -> product)
-- =========================

-- drop FK cũ
ALTER TABLE images
DROP CONSTRAINT IF EXISTS fk_image_variant;

-- drop column variant_id
ALTER TABLE images
DROP COLUMN IF EXISTS variant_id;

-- add product_id
ALTER TABLE images
ADD COLUMN product_id BIGINT NOT NULL;

-- add FK mới
ALTER TABLE images
ADD CONSTRAINT fk_images_product
FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE;

-- =========================
-- 3. product_variant: thêm image_id (1-1)
-- =========================
ALTER TABLE product_variant
ADD COLUMN image_id BIGINT;

ALTER TABLE product_variant
ADD CONSTRAINT fk_variant_image
FOREIGN KEY (image_id) REFERENCES images(image_id);

ALTER TABLE product_variant
ADD CONSTRAINT uq_variant_image UNIQUE (image_id);

-- =========================
-- 4. Move description
-- =========================

-- add description cho variant
ALTER TABLE product_variant
ADD COLUMN description TEXT;

-- drop description ở product
ALTER TABLE products
DROP COLUMN IF EXISTS description;

-- =========================
-- DONE
-- =========================