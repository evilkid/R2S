package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.ChiefHumanResourcesOfficer;
import tn.esprit.hrsmart.service.ChiefHumanResourcesOfficerFacade;
import tn.esprit.hrsmart.controller.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST controller for managing ChiefHumanResourcesOfficer.
 */
@Path("/api/chief-human-resources-officer")
public class ChiefHumanResourcesOfficerController {

    private final Logger log = LoggerFactory.getLogger(ChiefHumanResourcesOfficerController.class);

    @Inject
    private ChiefHumanResourcesOfficerFacade chiefHumanResourcesOfficerFacade;

    /**
     * POST : Create a new chiefHumanResourcesOfficer.
     *
     * @param chiefHumanResourcesOfficer the chiefHumanResourcesOfficer to
     * create
     * @return the Response with status 201 (Created) and with body the new
     * chiefHumanResourcesOfficer, or with status 400 (Bad Request) if the
     * chiefHumanResourcesOfficer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createChiefHumanResourcesOfficer(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer) throws URISyntaxException {
        log.debug("REST request to save ChiefHumanResourcesOfficer : {}", chiefHumanResourcesOfficer);
        chiefHumanResourcesOfficerFacade.create(chiefHumanResourcesOfficer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/chief-human-resources-officer/" + chiefHumanResourcesOfficer.getCin())),
                "chiefHumanResourcesOfficer", chiefHumanResourcesOfficer.getCin().toString())
                .entity(chiefHumanResourcesOfficer).build();
    }

    /**
     * PUT : Updates an existing chiefHumanResourcesOfficer.
     *
     * @param chiefHumanResourcesOfficer the chiefHumanResourcesOfficer to
     * update
     * @return the Response with status 200 (OK) and with body the updated
     * chiefHumanResourcesOfficer, or with status 400 (Bad Request) if the
     * chiefHumanResourcesOfficer is not valid, or with status 500 (Internal
     * Server Error) if the chiefHumanResourcesOfficer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateChiefHumanResourcesOfficer(ChiefHumanResourcesOfficer chiefHumanResourcesOfficer) throws URISyntaxException {
        log.debug("REST request to update ChiefHumanResourcesOfficer : {}", chiefHumanResourcesOfficer);
        chiefHumanResourcesOfficerFacade.edit(chiefHumanResourcesOfficer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "chiefHumanResourcesOfficer", chiefHumanResourcesOfficer.getCin().toString())
                .entity(chiefHumanResourcesOfficer).build();
    }

    /**
     * GET : get all the chiefHumanResourcesOfficers. <% if (pagination != 'no')
     * {} @param pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of
     * chiefHumanResourcesOfficers in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<ChiefHumanResourcesOfficer> getAllChiefHumanResourcesOfficers() {
        log.debug("REST request to get all ChiefHumanResourcesOfficers");
        List<ChiefHumanResourcesOfficer> chiefHumanResourcesOfficers = chiefHumanResourcesOfficerFacade.findAll();
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
        log.debug("REST request to get ChiefHumanResourcesOfficer : {}", cin);
        ChiefHumanResourcesOfficer chiefHumanResourcesOfficer = chiefHumanResourcesOfficerFacade.find(cin);
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
        log.debug("REST request to delete ChiefHumanResourcesOfficer : {}", cin);
        chiefHumanResourcesOfficerFacade.remove(chiefHumanResourcesOfficerFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "chiefHumanResourcesOfficer", cin.toString()).build();
    }

}
