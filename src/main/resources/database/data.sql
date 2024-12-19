MERGE INTO item AS target
USING (VALUES
    ('A', 'Perfumes', 40.0, CURRENT_DATE, CURRENT_DATE),
    ('B', 'Soap', 10.0, CURRENT_DATE, CURRENT_DATE),
    ('C', 'Shampoo', 30.0, CURRENT_DATE, CURRENT_DATE),
    ('D', 'Towel', 25.0, CURRENT_DATE, CURRENT_DATE),
    ('E', 'Creme', 13.0, CURRENT_DATE, CURRENT_DATE)
) AS source (id, name, normal_price, create_date, update_date)
ON target.id = source.id
WHEN NOT MATCHED THEN
    INSERT (id, name, normal_price, create_date, update_date)
    VALUES (source.id, source.name, source.normal_price, source.create_date, source.update_date);

MERGE INTO item_discount AS target
USING (VALUES
    ('1', 'A', 3, 30.0, DATE '2024-01-21', NULL),
    ('2', 'B', 2, 7.5, DATE '2024-05-23', NULL),
    ('3', 'C', 4, 20.0, DATE '2024-01-10', NULL),
    ('4', 'D', 2, 23.5, DATE '2024-02-25', NULL)
) AS source (id, item_id, quantity, special_price, valid_from, valid_to)
ON target.id = source.id
WHEN NOT MATCHED THEN
    INSERT (id, item_id, quantity, special_price, valid_from, valid_to)
    VALUES (source.id, source.item_id, source.quantity, source.special_price, source.valid_from, source.valid_to);

MERGE INTO bundle_discount AS target
USING (VALUES
    ('1', 'A', 'B', 20.0, CURRENT_DATE, DATE '2024-02-15'),
    ('2', 'C', 'D', 10.0, CURRENT_DATE, DATE '2024-01-11')
) AS source (id, from_item_id, to_item_id, discount_price, valid_from, valid_to)
ON target.id = source.id
WHEN NOT MATCHED THEN
    INSERT (id, from_item_id, to_item_id, discount_price, valid_from, valid_to)
    VALUES (source.id, source.from_item_id, source.to_item_id, source.discount_price, source.valid_from, source.valid_to);


/*
INSERT INTO item (id, name, normal_price, create_date, update_date)
VALUES 
('A', 'Perfumes', 40.0, CURRENT_DATE, CURRENT_DATE),
('B', 'Soap', 10.0, CURRENT_DATE, CURRENT_DATE),
('C', 'Shampoo', 30.0, CURRENT_DATE, CURRENT_DATE),
('D', 'Towel', 25.0, CURRENT_DATE, CURRENT_DATE),
('E', 'Creme', 13.0, CURRENT_DATE, CURRENT_DATE)
;

INSERT INTO item_discount (id, item_id, quantity, special_price, valid_from, valid_to)
VALUES
('1', 'A', 3, 30.0, CURRENT_DATE, DATE '2024-01-21'),
('2', 'B', 2, 7.5, CURRENT_DATE, DATE '2024-05-23'),
('3', 'C', 4, 20.0, CURRENT_DATE, DATE '2024-01-10'),
('4', 'D', 2, 23.5, CURRENT_DATE, DATE '2024-02-25')
;

INSERT INTO bundle_discount (id, from_item_id, to_item_id, discount_price, valid_from, valid_to)
VALUES
('1', 'A', 'B', 20.0, CURRENT_DATE, DATE '2024-02-15'),
('2', 'C', 'D', 10.0, CURRENT_DATE, DATE '2024-01-11')
;
*/