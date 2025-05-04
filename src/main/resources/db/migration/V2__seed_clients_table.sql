DELIMITER $$

DROP PROCEDURE IF EXISTS seed_clients$$

CREATE PROCEDURE seed_clients()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE generated_birth_date DATETIME;

    WHILE i < 100 DO
        SET generated_birth_date = CONCAT(
            DATE_SUB(CURDATE(), INTERVAL (FLOOR(18 + RAND() * 42)) YEAR),
            ' ',
            LPAD(FLOOR(RAND() * 24), 2, '0'), ':', -- Hour (00-23)
            LPAD(FLOOR(RAND() * 60), 2, '0'), ':', -- Minute (00-59)
            LPAD(FLOOR(RAND() * 60), 2, '0')      -- Second (00-59)
        );

        -- Ensure that the birth_date is within the valid TIMESTAMP range
        IF generated_birth_date >= '1970-01-01 00:00:01' AND generated_birth_date <= '2038-01-19 03:14:07' THEN
            INSERT INTO clients (name, lastname, age, birth_date)
            VALUES (
                CONCAT('Name', FLOOR(1 + RAND() * 9999)),
                CONCAT('Lastname', FLOOR(1 + RAND() * 9999)),
                FLOOR(18 + RAND() * 42), -- age between 18 and 60
                generated_birth_date
            );
        END IF;

        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

CALL seed_clients();
