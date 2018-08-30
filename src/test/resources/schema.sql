CREATE TABLE IF NOT EXISTS person (
    id           INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version      INTEGER NOT NULL,
    name         VARCHAR(50) NOT NULL,
    second_name  VARCHAR(50),
    surname      VARCHAR(50) NOT NULL,
    birth_date   DATE NOT NULL,
);

CREATE TABLE IF NOT EXISTS account (
    id              INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL,
    name            VARCHAR(50)  NOT NULL,
    balance         DECIMAL(19,4) NOT NULL,
    currency        VARCHAR(4) NOT NULL,
    person_id       INTEGER NOT NULL,
    CONSTRAINT person_FKEY   FOREIGN KEY (person_id) REFERENCES person(id)
);
