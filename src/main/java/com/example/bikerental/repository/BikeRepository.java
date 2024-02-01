package com.example.bikerental.repository;

import com.example.bikerental.model.Bike;
import com.example.bikerental.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BikeRepository {

    private final JdbcTemplate jdbcTemplate;



    public List<Bike> index() {
        return jdbcTemplate.query("SELECT * FROM Bike", new BeanPropertyRowMapper<>(Bike.class));
    }

    public Bike show(int id) {
        return jdbcTemplate.query("SELECT * FROM Bike WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Bike.class))
                .stream().findAny().orElse(null);
    }

    public void save(Bike bike) {
        jdbcTemplate.update("INSERT INTO Bike(name_bike, manufacturer, year) VALUES(?, ?, ?)", bike.getNameBike(),
                bike.getManufacturer(), bike.getYear());
    }

    public void update(int id, Bike updatedBike) {
        jdbcTemplate.update("UPDATE Bike SET name_bike=?, manufacturer=?, year=? WHERE id=?", updatedBike.getNameBike(),
                updatedBike.getManufacturer(), updatedBike.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Bike WHERE id=?", id);
    }

    public Optional<Person> getBikeOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Bike JOIN Person ON Bike.person_id = Person.id " +
                        "WHERE Bike.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Bike SET person_id=NULL WHERE id=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Bike SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}