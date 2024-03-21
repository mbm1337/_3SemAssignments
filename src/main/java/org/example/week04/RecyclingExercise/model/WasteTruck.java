package main.java.org.example.week04.RecyclingExercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "truck")
public class WasteTruck  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "registration_number")
    private String registrationNumber;

    @OneToMany(mappedBy = "truck")
    Set<Driver> drivers = new HashSet<>();

    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
        this.isAvailable = true;
    }


    public void addDriver(Driver driver) {
        this.drivers.add(driver);
        if (driver != null) {
            driver.setTruck(this);
        }
    }


}
