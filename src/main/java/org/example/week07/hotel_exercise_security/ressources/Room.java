package main.java.org.example.week07.hotel_exercise_security.ressources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import main.java.org.example.week07.hotel_exercise_security.DTO.RoomDTO;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int roomNumber;
    private boolean occupied;
    private float price;

    @ManyToOne
    @ToString.Exclude
    @JsonBackReference
    private Hotel hotel;

    public Room(int roomNumber, Hotel hotel) {
        this.roomNumber = roomNumber;
        this.hotel = hotel;
        occupied = false;
    }

    public Room(RoomDTO roomDTO) {
        this.id = roomDTO.id();
        this.roomNumber = roomDTO.number();
        this.price = roomDTO.price();
        if(hotel == null){
            //TODO: some way to add hotel to this by its id, maybe send hotel map as argument
        }
        if(hotel.getId() != roomDTO.hotelId()){
            throw new RuntimeException(); //TODO
        }
    }

    public Room(int roomNumber, float price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }



}
