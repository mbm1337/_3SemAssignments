package org.example.week03.Lombok;

import lombok.*;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    @Builder

    public class Person {
        private String firstName;
        private String lastName;
        private int age;
    }

