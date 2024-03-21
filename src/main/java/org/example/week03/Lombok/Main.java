package main.java.org.example.week03.Lombok;

import jakarta.persistence.PersistenceException;

public class Main {
    public static void main(String[] args) {
        Person person = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .build();

        Person person2 = Person.builder()
                .age(39)
                .lastName("")
                .firstName("")
                .build();




        System.out.println(person);

        person.setAge(26);
        System.out.println(person);


        System.out.println(person.equals(person2));

        System.out.println(person.hashCode());
        System.out.println(person2.hashCode());

        System.out.println(person.canEqual(person2));


    }
}
