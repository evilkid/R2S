package tn.esprit.R2S.resource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import tn.esprit.R2S.interfaces.*;
import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;
import tn.esprit.R2S.resource.util.TokenUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
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
    @EJB
    private ITokenService tokenService;

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
