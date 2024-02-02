package com.example.bikerental.repository.mapper;

import com.example.bikerental.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getLong(Person.Fields.id));
        person.setFullName(rs.getString(Person.Fields.fullName));
        person.setYearOfBirth(rs.getInt(rs.getInt(Person.Fields.yearOfBirth)));
        return person;
    }
}
