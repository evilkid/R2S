package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ITokenService;
import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.resource.util.TokenUtil;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by evilkid on 10/21/2016.
 */

@Path("/api")
public class AccountResource {
    @EJB
    private ITokenService tokenService;

    @EJB
    private IUsersService usersService;

    @Path("login/{username}/{password}")
    @GET
    //@Secured(Roles.ANONYMOUS)
    public Response login(@PathParam("username") String username, @PathParam("password") String password) {
        Users user = usersService.login(username, password);

        System.out.println("User found, adding coockie");

        return Optional.ofNullable(user)
                .map(u ->
                        Response.ok(u).cookie(
                                new NewCookie("access_token",
                                        TokenUtil.getToken(user, tokenService.getKey()),
                                        "/tn.esprit.R2S-web/resources/api/",
                                        "localhost",
                                        "",
                                        3600,
                                        true,
                                        true))
                                .build()
                )
                .orElseThrow(NotFoundException::new);
    }

    @Path("logout")
    @GET
    public Response logout() {
        return Response.ok().cookie(new NewCookie("access_token",
                "none",
                "/tn.esprit.R2S-web/resources/api/",
                "localhost",
                "",
                3600,
                true,
                true)).build();
    }
}
