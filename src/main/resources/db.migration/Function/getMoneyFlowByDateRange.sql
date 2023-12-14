CREATE OR REPLACE FUNCTION getMoneyFlowByDateRange(
    idAccount UUID,
    startDate TIMESTAMP,
    endDate TIMESTAMP
)
    RETURNS TABLE (totalEntrance DOUBLE PRECISION, totalExit DOUBLE PRECISION)
AS $$
BEGIN
    RETURN QUERY
        SELECT
            SUM(CASE WHEN category.type = 'credit' THEN transaction.amount ELSE 0 END) AS TotalEntrance,
            SUM(CASE WHEN category.type = 'debit' THEN transaction.amount ELSE 0 END) AS TotalExit
        FROM transaction
                 JOIN category  ON transaction.category = category.id
        WHERE transaction.Account = idAccount
          AND transaction.transactionDate BETWEEN startDate AND endDate;
END;
$$ LANGUAGE plpgsql;
