CREATE TABLE post_image (
    pi_id INTEGER GENERATED BY DEFAULT AS IDENTITY,
    pi_public_id VARCHAR(255),
    pi_url VARCHAR(255),
    p_id INTEGER,
    PRIMARY KEY (pi_id),
    FOREIGN KEY (p_id) REFERENCES post(p_id)
);