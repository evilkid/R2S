package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.service.UsersFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/users")
public class UsersController {


    @Inject
    private UsersFacade usersFacade;

    @POST
    public Response createUsers(Users users) throws URISyntaxException {
        usersFacade.create(users);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/users/" + users.getCin())),
                "users", users.getCin().toString())
                .entity(users).build();
    }

    @PUT
    public Response updateUsers(Users users) throws URISyntaxException {
        usersFacade.edit(users);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "users", users.getCin().toString())
                .entity(users).build();
    }

    @GET
    public List<Users> getAllUserss() {
        List<Users> userss = usersFacade.findAll();
        return userss;
    }

    @Path("/{cin}")
    @GET
    public Response getUsers(@PathParam("cin") Long cin) {
        Users users = usersFacade.find(cin);
        return Optional.ofNullable(users)
                .map(result -> Response.status(Response.Status.OK).entity(users).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{cin}")
    @DELETE
    public Response removeUsers(@PathParam("cin") Long cin) {
        usersFacade.remove(usersFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "users", cin.toString()).build();
    }

}
