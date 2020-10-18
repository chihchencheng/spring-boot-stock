CREATE TABLE stocks (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    stockCode VARCHAR (100) NOT NULL,
    modifiedDate timestamp,
    createdDate timestamp
);

-- CREATE TRIGGER trg_stock_updateModifiedDate
-- ON stock
-- AFTER UPDATE
-- AS
-- UPDATE stock
-- SET modifiedDate = CURRENT_TIMESTAMP
-- WHERE id IN (SELECT DISTINCT id FROM inserted);

