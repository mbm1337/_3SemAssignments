package org.example.week03.Unicorn;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "unicorns")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "powerStrength")
    @Min(value = 1, message = "Power strength must be at least 1")
    @Max(value = 100, message = "Power strength must be at most 100")
    private int powerStrength;
}
