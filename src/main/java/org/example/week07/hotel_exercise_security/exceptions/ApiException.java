package main.java.org.example.week07.hotel_exercise_security.exceptions;

public class ApiException extends RuntimeException{
    private int statusCode;
    public ApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }

}
