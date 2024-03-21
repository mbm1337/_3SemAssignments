package main.java.org.example.week07.hotel_exercise_security.DTO;


public record RoomDTO(
        int id,
        int hotelId,
        int number,
        float price
) {
}