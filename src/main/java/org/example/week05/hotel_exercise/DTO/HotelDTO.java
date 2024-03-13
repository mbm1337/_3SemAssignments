package org.example.week05.hotel_exercise.DTO;


import org.example.week05.hotel_exercise.ressources.Room;

import java.util.List;

public record HotelDTO(
        int id,
        String name,
        String address,
        List<Room> rooms
) {
}
