package com.example.airlineapi.exception;

public class ErrorMessages {
    public static final String USER_NOT_FOUND = "User not found with id: %d";
    public static final String FLIGHT_NOT_FOUND = "Flight not found with id: %d";
    public static final String NO_SEATS_AVAILABLE = "No available seats for flight with id: %d";
    public static final String INVALID_QUERY_PARAMETERS = "All query parameters must be provided";
    public static final String ROLE_NOT_FOUND = "Role not found: %s";
    public static final String BAD_CREDENTIALS = "Invalid email or password";
    public static final String EMAIL_ALREADY_IN_USE = "Error: Email is already in use!";


    private ErrorMessages() {
    }
}
