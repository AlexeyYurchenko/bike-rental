CREATE SCHEMA IF NOT EXISTS bike_schema;

CREATE TABLE IF NOT EXISTS person
(
    id Long PRIMARY KEY,
    full_name varchar(100) NOT NULL,
    year_of_birth int NOT NULL
);

CREATE TABLE IF NOT EXISTS bike
(
    id Long PRIMARY KEY,
    name_bike varchar(100) NOT NULL,
    manufacturer varchar(100) NOT NULL,
    year int NOT NULL,
    person_id Long REFERENCES person(id)
);