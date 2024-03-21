package main.java.org.example.week05.hotel_exercise.ressources;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import main.java.org.example.week05.hotel_exercise.DTO.HotelDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hotel")
    @JsonManagedReference
    private List<Room> rooms;
    public Hotel(){
        rooms = new ArrayList<>();
    }

    public Hotel(HotelDTO hotelDTO){
        this.id = hotelDTO.id();
        this.name = hotelDTO.name();
        this.address = hotelDTO.address();
        this.rooms = hotelDTO.rooms();
        rooms.forEach(r -> r.setHotel(this));
    }

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room){
        rooms.add(room);
        room.setHotel(this);
    }
}

