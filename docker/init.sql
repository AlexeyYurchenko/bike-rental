CREATE SCHEMA IF NOT EXISTS bike_schema;

CREATE TABLE IF NOT EXISTS Person
(
    id int PRIMARY KEY,
    full_name varchar(100) NOT NULL,
    year_of_birth int NOT NULL
);

CREATE TABLE IF NOT EXISTS Bike
(
    id int PRIMARY KEY,
    name_bike varchar(100) NOT NULL,
    manufacturer varchar(100) NOT NULL,
    "year" int NOT NULL,
    person_id int REFERENCES Person(id)
);