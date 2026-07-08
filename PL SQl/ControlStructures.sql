Scenario 1: Apply 1% Discount for Customers Above 60
  DECLARE
    CURSOR cust_cursor IS
        SELECT customer_id, age FROM customers;

BEGIN
    FOR cust_rec IN cust_cursor LOOP
        IF cust_rec.age > 60 THEN
            UPDATE loans
            SET interest_rate = interest_rate - 1
            WHERE customer_id = cust_rec.customer_id;
        END IF;
    END LOOP;

    COMMIT;
END;
/
Scenario 2: Promote Customers to VIP (Balance > 10,000)
DECLARE
    CURSOR cust_cursor IS
        SELECT customer_id, balance FROM customers;

BEGIN
    FOR cust_rec IN cust_cursor LOOP
        IF cust_rec.balance > 10000 THEN
            UPDATE customers
            SET IsVIP = 'TRUE'
            WHERE customer_id = cust_rec.customer_id;
        END IF;
    END LOOP;

    COMMIT;
END;
/
Scenario 3: Send Loan Due Reminders (Next 30 Days)
DECLARE
    CURSOR loan_cursor IS
        SELECT c.customer_name, l.due_date
        FROM customers c
        JOIN loans l ON c.customer_id = l.customer_id
        WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30;

BEGIN
    FOR loan_rec IN loan_cursor LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: Dear ' || loan_rec.customer_name ||
            ', your loan is due on ' || TO_CHAR(loan_rec.due_date, 'DD-MON-YYYY')
        );
    END LOOP;
END;
/
