package org.example.week05.hotel_exercise.config;

import io.javalin.Javalin;

public class ApplicationConfig {
    private static Javalin app;
    private static ApplicationConfig insanse;

    public static ApplicationConfig startServer(int port){
        app.start(port);
        return insanse;
    }


    public static ApplicationConfig initateServer(){
        app.create(config -> {
            config.http.defaultContentType = "application/json";
        });

        return insanse;
    }

    public static void stopServer() {
        app.stop();
    }
}
