package main.java.org.example.week07.hotel_exercise_security.controller;

import io.javalin.http.Handler;
import main.java.org.example.week07.hotel_exercise_security.DTO.UserDTO;

import java.util.Set;

public interface ISecurityController {
    Handler register();
    Handler login();

    String createToken(UserDTO user);


    boolean authorize(UserDTO user, Set<String> allowedRoles);

    Handler authenticate();
    UserDTO verifyToken(String token);
}
