INSERT INTO p_user (username, password, address, location)
SELECT
    'user_' || gs,
    'pw',
    '서울특별시 어딘가 ' || gs,
    ST_MakePoint(
        126.8 + random() * (127.2 - 126.8),
        37.4 + random() * (37.7 - 37.4)
    )::geography
FROM generate_series(1, 100000) AS gs;
