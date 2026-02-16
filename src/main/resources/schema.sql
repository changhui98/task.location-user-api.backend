CREATE INDEX idx_user_location
ON p_user
USING GIST (location);

CREATE INDEX idx_user_username
ON p_user (username);
