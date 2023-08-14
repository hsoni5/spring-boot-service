

INSERT INTO account
    (account_number, name, type, balance)
SELECT 50020, 'Hari', 'saving',2000.02
WHERE
    NOT EXISTS (
        SELECT account_number FROM account WHERE account_number = 50020
    );
    
INSERT INTO account
    (account_number, name, type, balance)
SELECT 55000, 'Vikas', 'current',5000.50
WHERE
    NOT EXISTS (
        SELECT account_number FROM account WHERE account_number = 55000
    );
INSERT INTO account
    (account_number, name, type, balance)
SELECT 30000, 'Nitesh', 'FSC',8000.03
WHERE
    NOT EXISTS (
        SELECT account_number FROM account WHERE account_number = 30000
    );
INSERT INTO account
    (account_number, name, type, balance)
SELECT 60000, 'Anna', 'saving',9000.90
WHERE
    NOT EXISTS (
        SELECT account_number FROM account WHERE account_number = 60000
    );
    