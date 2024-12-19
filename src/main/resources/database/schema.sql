CREATE TABLE IF NOT EXISTS item (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(255),
    normal_price DECIMAL(19, 1),
    create_date DATE DEFAULT CURRENT_DATE,
    update_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS item_discount (
    id VARCHAR(20) PRIMARY KEY,
    item_id VARCHAR(20),
    quantity INT,
    special_price DECIMAL(19, 1),
    valid_from DATE DEFAULT CURRENT_DATE,
    valid_to DATE
);
CREATE INDEX IF NOT EXISTS idx_item_discount_item_id ON item_discount (item_id);

CREATE TABLE IF NOT EXISTS bundle_discount (
    id VARCHAR(20) PRIMARY KEY,
    from_item_id VARCHAR(20),
    to_item_id VARCHAR(20),
    discount_price DECIMAL(19, 2),
    valid_from DATE DEFAULT CURRENT_DATE,
    valid_to DATE
);
CREATE INDEX IF NOT EXISTS idx_bundle_discount_from_to_item_id ON bundle_discount (from_item_id, to_item_id);
