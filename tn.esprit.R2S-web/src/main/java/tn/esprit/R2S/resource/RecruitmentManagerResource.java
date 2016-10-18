package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.RecruitmentManager;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.RecruitmentManagerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RecruitmentManager.
 */
@Path("/api/recruitment-manager")

public class RecruitmentManagerResource {


    @EJB
    private RecruitmentManagerService recruitmentManagerService;

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

        recruitmentManagerService.create(recruitmentManager);
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

        recruitmentManagerService.edit(recruitmentManager);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "recruitmentManager", recruitmentManager.getCin().toString())
                .entity(recruitmentManager).build();
    }

    /**
     * GET : get all the recruitmentManagers. <% if (pagination != 'no') {}
     *
     * @param pageable the p
     *                 <p>
     *                 agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     *                 filter the filter of the r
     *                 equest<% }}
     * @return the Response with status 200 (OK) and the list of
     * recruitmentManagers in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<RecruitmentManager> getAllRecruitmentManagers() {

        List<RecruitmentManager> recruitmentManagers = recruitmentManagerService.findAll();
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

        RecruitmentManager recruitmentManager = recruitmentManagerService.find(cin);
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

        recruitmentManagerService.remove(recruitmentManagerService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "recruitmentManager", cin.toString()).build();
    }

}
