-- PostGIS 확장 활성화
CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS postgis_topology;

-- user 테이블 생성
CREATE TABLE p_user (
    id BIGSERIAL PRIMARY KEY,

    username VARCHAR(50) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    address VARCHAR(255),

    location geography(Point, 4326) NOT NULL
);

-- 인덱스 설정
CREATE INDEX idx_user_location
ON p_user
USING GIST (location);

CREATE INDEX idx_user_username
ON p_user (username);

-- 더미 데이터 10만건 생성
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
