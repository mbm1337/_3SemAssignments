package org.example.week04.RecyclingExercise.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "driver")
@Entity
public class Driver {
    @Id
    private String id;

    @Column(name = "employment_date")
    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToOne
    private WasteTruck truck;



    public Driver( String name, String surname, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.employmentDate = new Date();
    }


    public String createId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String date = dateFormat.format(employmentDate);

        String initials = name.charAt(0) + surname.charAt(0) + "";

        Random random = new Random();
        int number = random.nextInt(900) + 100;

        char lastLetterSurname = surname.toUpperCase().charAt(surname.length() - 1);

        String driverId = date + "-" + initials + "-" + number + lastLetterSurname;

        if (validateDriverId(driverId)) {
            return driverId;
        } else {
            return "Invalid driver id";
        }

    }

    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }

    @PrePersist
    public void prePersist() {
        this.employmentDate = new Date();
        this.id = createId();

    }


}
