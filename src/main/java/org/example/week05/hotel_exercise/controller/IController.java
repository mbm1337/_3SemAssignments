package org.example.week05.hotel_exercise.controller;

import io.javalin.http.Handler;

public interface IController {
    public Handler getAll();
    public Handler getById();
    public Handler create();
    public Handler delete();
    public Handler update();
}