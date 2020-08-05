INSERT INTO currency_table(cbr_id, char_code, name, nominal, num_code)
VALUES ('', 'RUB', 'Российский рубль', 1, 0);

INSERT INTO rates(rate_date, rate_value, currency_id)
SELECT '2020-08-05' AS rate_date, 1 AS rate_value, id AS currency_id
FROM currency_table
WHERE char_code = 'RUB';