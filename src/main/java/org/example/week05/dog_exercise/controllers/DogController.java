package org.example.week05.dog_exercise.controllers;

import io.javalin.http.Handler;
import org.example.week05.dog_exercise.dtos.DogDTO;


import java.util.Map;


public class DogController {
    Map<Integer, DogDTO> dogs = Map.of(
            1, new DogDTO(1, "Buddy", "Golden Retriever", "male", 3),
               2, new DogDTO(2, "Molly", "Golden Retriever", "female", 5),
                3, new DogDTO(3, "Max", "Golden Retriever" , "male", 2)
    );


    public Handler getAllDogs(){
        return ctx -> {
            ctx.json(dogs);
        };

    }


    public Handler getDogById(){
        return ctx -> {
             int id = Integer.parseInt(ctx.pathParam("id"));
                DogDTO dog = dogs.get(id);
                if(dog != null){
                 ctx.json(dog);
                }else{
                 ctx.status(404);
                }
        };
    }

    public Handler createDog(){
        return ctx -> {
            DogDTO dog = ctx.bodyAsClass(DogDTO.class);
            dogs.put(dog.getId(), dog);
            ctx.status(201);
        };
    }

    public Handler updateDog(){
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DogDTO dog = ctx.bodyAsClass(DogDTO.class);
            dogs.put(id, dog);
            ctx.status(204);
        };

    }

    public Handler deleteDog(){
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            dogs.remove(id);
            ctx.status(204);
        };
    }



}
