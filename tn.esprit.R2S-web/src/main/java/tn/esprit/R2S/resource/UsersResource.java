package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Users;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.UsersService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Users.
 */
@Path("/api/users")

public class UsersResource {


    @EJB
    private UsersService usersService;

    /**
     * POST : Create a new users.
     *
     * @param users the users to create
     * @return the Response with status 201 (Created) and with body the new
     * users, or with status 400 (Bad Request) if the users has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createUsers(Users users) throws URISyntaxException {

        usersService.create(users);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/users/" + users.getCin())),
                "users", users.getCin().toString())
                .entity(users).build();
    }

    /**
     * PUT : Updates an existing users.
     *
     * @param users the users to update
     * @return the Response with status 200 (OK) and with body the updated
     * users, or with status 400 (Bad Request) if the users is not valid, or
     * with status 500 (Internal Server Error) if the users couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateUsers(Users users) throws URISyntaxException {

        usersService.edit(users);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "users", users.getCin().toString())
                .entity(users).build();
    }

    /**
     * GET : get all the userss. <% if (pagination != 'no') {} @param pageable
     * the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of userss in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Users> getAllUserss() {

        List<Users> userss = usersService.findAll();
        return userss;
    }

    /**
     * GET /:cin : get the "cin" users.
     *
     * @param cin the cin of the users to retrieve
     * @return the Response with status 200 (OK) and with body the users, or
     * with status 404 (Not Found)
     */
    @Path("/{cin}")
    @GET
    public Response getUsers(@PathParam("cin") Long cin) {

        Users users = usersService.find(cin);
        return Optional.ofNullable(users)
                .map(result -> Response.status(Response.Status.OK).entity(users).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:cin : remove the "cin" users.
     *
     * @param cin the cin of the users to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{cin}")
    @DELETE
    public Response removeUsers(@PathParam("cin") Long cin) {

        usersService.remove(usersService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "users", cin.toString()).build();
    }

}
