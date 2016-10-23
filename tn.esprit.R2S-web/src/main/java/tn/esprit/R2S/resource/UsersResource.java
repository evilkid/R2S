package tn.esprit.R2S.resource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import tn.esprit.R2S.interfaces.ITokenService;
import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;
import tn.esprit.R2S.resource.util.TokenUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/users")
@Secured(Roles.CHIEF_HUMAN_RESOURCES_OFFICER)
public class UsersResource {


    @EJB
    private ITokenService tokenService;
    @EJB
    private IUsersService usersService;

    @POST
    public Response createUser(Users user) throws URISyntaxException {
        usersService.create(user);

        return Response.created(new URI("/resources/api/users/" + user.getCin())).entity(user).build();
    }

    @PUT
    public Response updateUser(Users user) {
        usersService.edit(user);
        return Response.ok(user).build();
    }

    @GET
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @Path("/{cin}")
    @GET
    public Response getUser(@PathParam("cin") Long cin) {

        return Optional.ofNullable(usersService.find(cin))
                .map(user -> Response.ok(user).build())
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{cin}")
    @DELETE
    public Response removeUsers(@PathParam("cin") Long cin) {

        /*return Optional.ofNullable(usersService.find(cin))
                .map(user -> {
                    usersService.remove(user);
                    return Response.ok().build();
                }).orElseThrow(NotFoundException::new);*/
        return disable(cin);
    }

    @Path("/{cin}/disable")
    @PUT
    public Response disable(@PathParam("cin") Long cin) {
        usersService.disable(cin);
        return Response.ok().build();
    }

    @Path("/{cin}/enable")
    @PUT
    public Response enable(@PathParam("cin") Long cin) {
        usersService.enable(cin);
        return Response.ok().build();
    }

    @Path("/referred")
    @GET
    @Secured(Roles.EMPLOYEE)
    public Response getReferred(@CookieParam("access_token") Cookie cookie) {
        Jws<Claims> claims = TokenUtil.getClaims(cookie, tokenService.getKey());

        Long cin = Long.parseLong((String) claims.getBody().get("cin"));

        return Response.ok(usersService.getReferred(cin)).build();
    }

}
