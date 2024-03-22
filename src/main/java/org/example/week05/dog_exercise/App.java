package org.example.week05.dog_exercise;


import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import org.example.week05.dog_exercise.controllers.DogController;


import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create()
                .start(7007);
        app.routes(getDogsResource());

    }

    private static EndpointGroup getDogsResource(){
        DogController dogCtl = new DogController();
        return () -> {
            path("/api/dogs", () -> {
                get(dogCtl.getAllDogs());
                post("/dog", dogCtl.createDog());
                path("/dog/{id}", () -> {
                    get(dogCtl.getDogById());
                    put(dogCtl.updateDog());
                    delete(dogCtl.deleteDog());
                });
            });

        };
    }


}

