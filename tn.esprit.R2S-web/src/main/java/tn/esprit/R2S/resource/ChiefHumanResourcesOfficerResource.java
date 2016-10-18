package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.ChiefHumanResourcesOfficer;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.ChiefHumanResourcesOfficerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ChiefHumanResourcesOfficer.
 */
@Path("/api/chief-human-resources-officer")

public class ChiefHumanResourcesOfficerResource {


    @EJB
    private ChiefHumanResourcesOfficerService chiefHumanResourcesOfficerService;

    /**
     * POST : Create a new chiefHumanResourcesOfficer.
     *
     * @param chiefHumanResourcesOfficer the chiefHumanResourcesOfficer to
     *                                   create
     * @return the Response with status 201 (Created) and with body the new
     * chiefHumanResourcesOfficer, or with status 400 (Bad Request) if the
     * chiefHumanResourcesOfficer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createChiefHumanResourcesOfficer(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer) throws URISyntaxException {

        chiefHumanResourcesOfficerService.create(chiefHumanResourcesOfficer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/chief-human-resources-officer/" + chiefHumanResourcesOfficer.getCin())),
                "chiefHumanResourcesOfficer", chiefHumanResourcesOfficer.getCin().toString())
                .entity(chiefHumanResourcesOfficer).build();
    }

    /**
     * PUT : Updates an existing chiefHumanResourcesOfficer.
     *
     * @param chiefHumanResourcesOfficer the chiefHumanResourcesOfficer to
     *                                   update
     * @return the Response with status 200 (OK) and with body the updated
     * chiefHumanResourcesOfficer, or with status 400 (Bad Request) if the
     * chiefHumanResourcesOfficer is not valid, or with status 500 (Internal
     * Server Error) if the chiefHumanResourcesOfficer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateChiefHumanResourcesOfficer(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer) throws URISyntaxException {

        chiefHumanResourcesOfficerService.edit(chiefHumanResourcesOfficer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "chiefHumanResourcesOfficer", chiefHumanResourcesOfficer.getCin().toString())
                .entity(chiefHumanResourcesOfficer).build();
    }

    /**
     * GET : get all the chiefHumanResourcesOfficers. <% if (pagination != 'no')
     * {} @param pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of
     * chiefHumanResourcesOfficers in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<ChiefHumanResourcesOfficer> getAllChiefHumanResourcesOfficers() {

        List<ChiefHumanResourcesOfficer> chiefHumanResourcesOfficers = chiefHumanResourcesOfficerService.findAll();
        return chiefHumanResourcesOfficers;
    }

    /**
     * GET /:cin : get the "cin" chiefHumanResourcesOfficer.
     *
     * @param cin the cin of the chiefHumanResourcesOfficer to retrieve
     * @return the Response with status 200 (OK) and with body the
     * chiefHumanResourcesOfficer, or with status 404 (Not Found)
     */
    @Path("/{cin}")
    @GET
    public Response getChiefHumanResourcesOfficer(@PathParam("cin") Long cin) {

        ChiefHumanResourcesOfficer chiefHumanResourcesOfficer = chiefHumanResourcesOfficerService.find(cin);
        return Optional.ofNullable(chiefHumanResourcesOfficer)
                .map(result -> Response.status(Response.Status.OK).entity(chiefHumanResourcesOfficer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:cin : remove the "cin" chiefHumanResourcesOfficer.
     *
     * @param cin the cin of the chiefHumanResourcesOfficer to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{cin}")
    @DELETE
    public Response removeChiefHumanResourcesOfficer(@PathParam("cin") Long cin) {

        chiefHumanResourcesOfficerService.remove(chiefHumanResourcesOfficerService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "chiefHumanResourcesOfficer", cin.toString()).build();
    }

}
