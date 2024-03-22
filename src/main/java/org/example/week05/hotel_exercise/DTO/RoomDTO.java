package org.example.week05.hotel_exercise.DTO;


public record RoomDTO(
        int id,
        int hotelId,
        int number,
        float price
) {
}