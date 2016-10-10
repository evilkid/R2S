package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.RecruitmentManager;
import tn.esprit.R2S.service.RecruitmentManagerFacade;
import tn.esprit.R2S.controller.util.HeaderUtil;
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
 * REST controller for managing RecruitmentManager.
 */
@Path("/api/recruitment-manager")
public class RecruitmentManagerController {

    private final Logger log = LoggerFactory.getLogger(RecruitmentManagerController.class);

    @Inject
    private RecruitmentManagerFacade recruitmentManagerFacade;

    /**
     * POST : Create a new recruitmentManager.
     *
     * @param recruitmentManager the recruitmentManager to create
     * @return the Response with status 201 (Created) and with body the new
     * recruitmentManager, or with status 400 (Bad Request) if the
     * recruitmentManager has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createRecruitmentManager(RecruitmentManager recruitmentManager) throws URISyntaxException {
        log.debug("REST request to save RecruitmentManager : {}", recruitmentManager);
        recruitmentManagerFacade.create(recruitmentManager);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/recruitment-manager/" + recruitmentManager.getCin())),
                "recruitmentManager", recruitmentManager.getCin().toString())
                .entity(recruitmentManager).build();
    }

    /**
     * PUT : Updates an existing recruitmentManager.
     *
     * @param recruitmentManager the recruitmentManager to update
     * @return the Response with status 200 (OK) and with body the updated
     * recruitmentManager, or with status 400 (Bad Request) if the
     * recruitmentManager is not valid, or with status 500 (Internal Server
     * Error) if the recruitmentManager couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateRecruitmentManager(RecruitmentManager recruitmentManager) throws URISyntaxException {
        log.debug("REST request to update RecruitmentManager : {}", recruitmentManager);
        recruitmentManagerFacade.edit(recruitmentManager);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "recruitmentManager", recruitmentManager.getCin().toString())
                .entity(recruitmentManager).build();
    }

    /**
     * GET : get all the recruitmentManagers. <% if (pagination != 'no') {}
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of
     * recruitmentManagers in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<RecruitmentManager> getAllRecruitmentManagers() {
        log.debug("REST request to get all RecruitmentManagers");
        List<RecruitmentManager> recruitmentManagers = recruitmentManagerFacade.findAll();
        return recruitmentManagers;
    }

    /**
     * GET /:cin : get the "cin" recruitmentManager.
     *
     * @param cin the cin of the recruitmentManager to retrieve
     * @return the Response with status 200 (OK) and with body the
     * recruitmentManager, or with status 404 (Not Found)
     */
    @Path("/{cin}")
    @GET
    public Response getRecruitmentManager(@PathParam("cin") Long cin) {
        log.debug("REST request to get RecruitmentManager : {}", cin);
        RecruitmentManager recruitmentManager = recruitmentManagerFacade.find(cin);
        return Optional.ofNullable(recruitmentManager)
                .map(result -> Response.status(Response.Status.OK).entity(recruitmentManager).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:cin : remove the "cin" recruitmentManager.
     *
     * @param cin the cin of the recruitmentManager to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{cin}")
    @DELETE
    public Response removeRecruitmentManager(@PathParam("cin") Long cin) {
        log.debug("REST request to delete RecruitmentManager : {}", cin);
        recruitmentManagerFacade.remove(recruitmentManagerFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "recruitmentManager", cin.toString()).build();
    }

}
