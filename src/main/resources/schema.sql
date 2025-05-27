CREATE TABLE IF NOT EXISTS run(
    id SERIAL,
    title VARCHAR(255) NOT NULL,
    started_on TIMESTAMP NOT NULL,
    completed_on TIMESTAMP NOT NULL,
    miles INT NOT NULL,
    location VARCHAR(50) NOT NULL,
    version INT,
    PRIMARY KEY (id)
);