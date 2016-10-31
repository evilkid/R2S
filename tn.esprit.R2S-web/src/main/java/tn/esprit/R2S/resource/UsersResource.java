package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.interfaces.IEmployeeService;
import tn.esprit.R2S.interfaces.IRecruitmentManagerService;
import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.resource.util.Roles;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api/users")
//@Secured(Roles.CHIEF_HUMAN_RESOURCES_OFFICER)
public class UsersResource {

    @EJB
    private IUsersService usersService;
    @EJB
    private IEmployeeService employeeService;
    @EJB
    private ICandidateService candidateService;
    @EJB
    private IRecruitmentManagerService recruitmentManagerService;

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
    public Response getAllUserss(@QueryParam("role") Roles role) {
        if (role != null) {
            switch (role) {
                case CANDIDATE:
                    return Response.ok(candidateService.findAll()).build();
                case EMPLOYEE:
                    return Response.ok(employeeService.findAll()).build();
                case RECRUITMENT_MANAGER:
                    return Response.ok(employeeService.findAll()).build();
            }
        }

        return Response.ok(usersService.findAll()).build();
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
