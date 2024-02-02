package com.example.bikerental.repository.mapper;

import com.example.bikerental.model.Bike;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BikeRowMapper implements RowMapper<Bike> {

    @Override
    public Bike mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bike bike = new Bike();

        bike.setId(rs.getLong(Bike.Fields.id));
        bike.setNameBike(rs.getString(Bike.Fields.nameBike));
        bike.setManufacturer(rs.getString(Bike.Fields.manufacturer));
        bike.setYear(rs.getInt(Bike.Fields.year));
        return  bike;
    }
}
