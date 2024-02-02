package com.example.bikerental.repository;

import com.example.bikerental.exceptions.PersonNotFoundException;
import com.example.bikerental.model.Bike;
import com.example.bikerental.model.Person;
import com.example.bikerental.repository.mapper.BikeRowMapper;
import com.example.bikerental.repository.mapper.PersonRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Repository
public class PersonRepository {
    private final JdbcTemplate jdbcTemplate;



    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    public Optional<Person> show(Long id) {
        String sql = "SELECT * FROM person WHERE ID = ?";
        Person person = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new PersonRowMapper(),1)
                )
        );
        return Optional.ofNullable(person);
    }

    public Person save(Person person) {
        person.setId(System.currentTimeMillis());
        String sql = "INSERT INTO person(full_name, year_of_birth, id) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, person.getFullName(),
                person.getYearOfBirth(),person.getId());
        return person;
    }

    public Person update(Person person) {
        Person existedPerson = show(person.getId()).orElse(null);
        String sql = "UPDATE Person SET full_name=?, year_of_birth=? WHERE id=?";
        if (existedPerson != null) {
            jdbcTemplate.update(sql, person.getFullName(),
                    person.getYearOfBirth(),person.getId());
            return person;
        }
        throw new PersonNotFoundException("Person for update not found ID" + person.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM Person WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Bike> getBikeByPersonId(Long id) {
        return jdbcTemplate.query("SELECT * FROM Bike WHERE person_id = ?", new Object[]{id},
                new BikeRowMapper());
    }
}