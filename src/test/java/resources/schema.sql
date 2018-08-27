CREATE TABLE IF NOT EXISTS organization (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL,
    name        VARCHAR(50) NOT NULL,
    full_Name   VARCHAR(50) NOT NULL,
    inn         INTEGER NOT NULL,
    kpp         INTEGER NOT NULL,
    ur_Address  VARCHAR(50) NOT NULL,
    phone_org       INTEGER ,
    is_Active   BOOLEAN
);

CREATE TABLE IF NOT EXISTS account (
    id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER NOT NULL,
    name                VARCHAR(50),
    Address             VARCHAR(50),
    phone_office        VARCHAR(50),
    is_Active           BOOLEAN,
    organization_id     INTEGER ,
    CONSTRAINT office_FKEY   FOREIGN KEY (organization_id) REFERENCES organization(id)
);

CREATE TABLE IF NOT EXISTS doc (
   id                  INTEGER  PRIMARY KEY AUTO_INCREMENT,
   version              INTEGER NOT NULL,
   doc_name             VARCHAR(50) NOT NULL UNIQUE ,
   doc_code             INTEGER  NOT NULL UNIQUE,
   CONSTRAINT doc_FKEY_doc_name  FOREIGN KEY (doc_name) REFERENCES doc(doc_name),

);

CREATE TABLE IF NOT EXISTS country (
   id                   INTEGER  PRIMARY KEY AUTO_INCREMENT,
   version              INTEGER NOT NULL,
   country_name         VARCHAR(50) NOT NULL UNIQUE,
   country_code         INTEGER(50)  NOT NULL UNIQUE,
   citizenship_code     VARCHAR(250)  NOT NULL UNIQUE,
   CONSTRAINT country_FKEY_citizenship_code   FOREIGN KEY (citizenship_code) REFERENCES country(citizenship_code)
);

CREATE TABLE IF NOT EXISTS user (
    id                 INTEGER      PRIMARY KEY AUTO_INCREMENT,
   version             INTEGER      NOT NULL,
   first_name          VARCHAR(50)  ,
   middle_name         VARCHAR(50)  ,
   second_name         VARCHAR(50)  ,
   position            VARCHAR(50)  ,
   phone_user          INTEGER      ,
   doc_date            DATE         ,
   doc_number          INTEGER      ,
   is_Identified       BOOLEAN     ,
   office_id           INTEGER  ,
   doc_id              INTEGER  ,
   country_id          INTEGER  ,
   citizenship_code    VARCHAR(50)  ,

   CONSTRAINT user_FKEY_doc   FOREIGN KEY (doc_id) REFERENCES doc(id),
   CONSTRAINT user_FKEY_office   FOREIGN KEY (office_id) REFERENCES account(id),
   CONSTRAINT user_FKEY_country   FOREIGN KEY (citizenship_code) REFERENCES country(citizenship_code)
);
