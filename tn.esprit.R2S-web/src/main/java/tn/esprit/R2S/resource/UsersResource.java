package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/users")
@Secured(Roles.CHIEF_HUMAN_RESOURCES_OFFICER)
public class UsersResource {

    @EJB
    private IUsersService usersService;

    @POST
    public Response createUsers(Users users) {
        usersService.create(users);
        return Response.status(Response.Status.CREATED).entity(users).build();
    }

    @PUT
    public Response updateUsers(Users users) {
        usersService.edit(users);
        return Response.ok(users).build();
    }

    @GET
    public List<Users> getAllUserss() {
        return usersService.findAll();
    }

    @Path("/{cin}")
    @GET
    public Response getUsers(@PathParam("cin") Long cin) {

        return Optional.ofNullable(usersService.find(cin))
                .map(user -> Response.ok(user).build())
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{cin}")
    @DELETE
    public Response removeUsers(@PathParam("cin") Long cin) {

        return Optional.ofNullable(usersService.find(cin))
                .map(user -> {
                    usersService.remove(user);
                    return Response.ok().build();
                }).orElseThrow(NotFoundException::new);
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

}
