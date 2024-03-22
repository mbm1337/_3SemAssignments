package org.example.week06.security;

import io.javalin.apibuilder.EndpointGroup;
import org.example.week06.security.exception.ApiException;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class UserRoutes {
    private final UserController userController = new UserController();

    protected EndpointGroup getRoutes() {

        return () -> {
            path("/auth", () -> {
                post("/login", ctx -> {
                    try {
                        userController.login(ctx);
                    } catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
                }, RouteRoles.ANYONE);

                post("/register", ctx -> {
                    try {
                        userController.register(ctx);
                    } catch (ApiException e) {
                        ctx.status(400).result(e.getMessage());
                    }
                }, RouteRoles.ANYONE);
            });
        };
    }
}
