package org.example.week05.hotel_exercise.DAO;

public abstract class DAO implements IDAO{
    public void getAll() {
        System.out.println("Getting all");
    }

    public void getById() {
        System.out.println("Getting by id");

    }

}
