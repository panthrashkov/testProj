INSERT INTO person (id, version, name, second_name, surname, birth_date)
VALUES (100, 0, 'TestPerson', 'TestSecondName',  'TestSurname', '1986-08-15');

INSERT INTO account (id, version, name, balance, currency, person_id)
VALUES (100, 0, 'TestAccount', 15.5,  'RUB', 100);