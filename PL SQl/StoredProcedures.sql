Scenario 1: Process Monthly Interest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN
    UPDATE accounts
    SET balance = balance + (balance * 0.01)
    WHERE account_type = 'SAVINGS';

    COMMIT;
END;
/
Scenario 2: Update Employee Bon
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
)
IS
BEGIN
    UPDATE employees
    SET salary = salary + (salary * p_bonus_percent / 100)
    WHERE department = p_department;

    COMMIT;
END;
/
Scenario 3: Transfer Funds

Stored Procedure: TransferFunds

CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    -- Get source account balance
    SELECT balance
    INTO v_balance
    FROM accounts
    WHERE account_id = p_from_account;

    -- Check sufficient balance
    IF v_balance >= p_amount THEN

        -- Deduct from source account
        UPDATE accounts
        SET balance = balance - p_amount
        WHERE account_id = p_from_account;

        -- Add to destination account
        UPDATE accounts
        SET balance = balance + p_amount
        WHERE account_id = p_to_account;

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Transfer Successful.');

    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient Balance.');
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Account not found.');
END;
/

Execute:

BEGIN
    TransferFunds(101, 102, 5000);
END;
/
