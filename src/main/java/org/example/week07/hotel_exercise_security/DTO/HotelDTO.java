package org.example.week07.hotel_exercise_security.DTO;

import org.example.week07.hotel_exercise_security.ressources.Room;

import java.util.List;

public record HotelDTO(
        int id,
        String name,
        String address,
        List<Room> rooms
) {
}
