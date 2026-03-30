-- =========================
-- V5: Refactor variant system
-- Remove variant_option, move to attribute_id + value
-- =========================

-- 1. Thêm attribute_id vào product_variant_option
ALTER TABLE product_variant_option
ADD COLUMN attribute_id BIGINT;

-- 2. Migrate data từ variant_option → product_variant_option
UPDATE product_variant_option pvo
SET attribute_id = vo.attribute_id
FROM variant_option vo
WHERE pvo.option_id = vo.option_id;

-- 3. Set NOT NULL sau khi migrate
ALTER TABLE product_variant_option
ALTER COLUMN attribute_id SET NOT NULL;

-- 4. Drop FK cũ tới variant_option
ALTER TABLE product_variant_option
DROP CONSTRAINT IF EXISTS fk_pvo_option;

-- 5. Drop column option_id
ALTER TABLE product_variant_option
DROP COLUMN option_id;

-- 6. Drop bảng variant_option (không dùng nữa)
DROP TABLE IF EXISTS variant_option;

-- 7. Drop PK cũ
ALTER TABLE product_variant_option
DROP CONSTRAINT IF EXISTS product_variant_option_pkey;

-- 8. Add PK mới (variant_id + attribute_id)
ALTER TABLE product_variant_option
ADD CONSTRAINT product_variant_option_pkey
PRIMARY KEY (variant_id, attribute_id);

-- 9. Add FK tới variant_attribute
ALTER TABLE product_variant_option
ADD CONSTRAINT fk_pvo_attribute
FOREIGN KEY (attribute_id)
REFERENCES variant_attribute(attribute_id)
ON DELETE CASCADE;

-- 10. Index để query nhanh
CREATE INDEX idx_pvo_attribute ON product_variant_option(attribute_id);