package org.example.week06.security;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.security.RouteRole;
import org.example.week06.security.dto.UserDTO;
import org.example.week06.security.exception.AuthorizationException;
import org.example.week06.security.dto.UserDTO;

import java.util.Set;

public class AccessManagerController
{

    private final TokenFactory TOKEN_FACTORY = TokenFactory.getInstance();

    public void accessManagerHandler(Handler handler, Context ctx, Set<? extends RouteRole> permittedRoles) throws Exception, ApiException {
        String path = ctx.path();
        boolean isAuthorized = false;

        if (path.equals("/api/v1/auth/login") || path.equals("/api/v1/auth/register") || path.equals("/api/v1/routes") || permittedRoles.contains(RouteRoles.ANYONE))
        {
            handler.handle(ctx);
            return;
        } else
        {
            RouteRole[] userRole = getUserRole(ctx);
            for (RouteRole role : userRole)
            {
                if (permittedRoles.contains(role))
                {
                    isAuthorized = true;
                    break;
                }
            }
        }

        if (isAuthorized)
        {
            handler.handle(ctx);
        } else
        {
            throw new AuthorizationException(401, "You are not authorized to perform this action");
        }
    }

    private RouteRole[] getUserRole(Context ctx) throws AuthorizationException, ApiException
    {
        try
        {
            String token = ctx.header("Authorization").split(" ")[1];
            UserDTO userDTO = TOKEN_FACTORY.verifyToken(token);
            return userDTO.getRoles().stream().map(r -> RouteRoles.valueOf(r.toUpperCase())).toArray(RouteRole[]::new);
        }
        catch (NullPointerException e)
        {
            throw new ApiException(401, "Invalid token");
        }

    }
}