    CREATE OR REPLACE FUNCTION getCategoryAmountsByDateRangeWithZero(
        idAccount UUID,
        startDate TIMESTAMP,
        endDate TIMESTAMP
    )
        RETURNS TABLE (categoryID UUID, categoryName VARCHAR(250), totalAmount DOUBLE PRECISION)
    AS $$
    BEGIN
        RETURN QUERY
            SELECT c.name AS categoryName,
                   COALESCE(SUM(CASE WHEN t.account = idAccount THEN t.amount ELSE 0 END), 0) AS totalAmount
            FROM category c
                     LEFT JOIN transaction t ON c.id = t.category
                AND t.transactionDate BETWEEN startDate AND endDate
            GROUP BY c.id, c.name;
    END;
    $$ LANGUAGE plpgsql;
