package com.example.bikerental.exceptions;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String message) {
        super(message);
    }
}