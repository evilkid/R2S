package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/users")
public class UsersResource {


    @EJB
    private IUsersService usersService;

    @POST
    public Response createUsers(Users users) throws URISyntaxException {

        usersService.create(users);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/users/" + users.getCin())),
                "users", users.getCin().toString())
                .entity(users).build();
    }

    @PUT
    public Response updateUsers(Users users) throws URISyntaxException {

        usersService.edit(users);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "users", users.getCin().toString())
                .entity(users).build();
    }
    @GET
    public List<Users> getAllUserss() {

        List<Users> userss = usersService.findAll();
        return userss;
    }
    @Path("/{cin}")
    @GET
    public Response getUsers(@PathParam("cin") Long cin) {

        Users users = usersService.find(cin);
        return Optional.ofNullable(users)
                .map(result -> Response.status(Response.Status.OK).entity(users).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{cin}")
    @DELETE
    public Response removeUsers(@PathParam("cin") Long cin) {

        usersService.remove(usersService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "users", cin.toString()).build();
    }

}
