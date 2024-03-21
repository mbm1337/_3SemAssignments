package main.java.org.example.week07.hotel_exercise_security.controller;

import io.javalin.http.Handler;

public interface IController {
    public Handler getAll();
    public Handler getById();
    public Handler create();
    public Handler delete();
    public Handler update();
}