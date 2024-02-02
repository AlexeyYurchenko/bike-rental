package com.example.bikerental.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Bike {
    private Long id;

    @NotEmpty(message = "Название велосипеда не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название велосипеда должно быть от 2 до 100 символов длиной")
    private String nameBike;

    @NotEmpty(message = "Производители не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя производителя должно быть от 2 до 100 символов длиной")
    private String manufacturer;

    @Min(value = 2010, message = "Год должен быть больше, чем 2010")
    private int year;

}