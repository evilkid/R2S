package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.CandidateQuizModel;
import tn.esprit.R2S.service.CandidateQuizModelFacade;
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
 * REST controller for managing CandidateQuizModel.
 */
@Path("/api/candidate-quiz-model")
public class CandidateQuizModelController {

    private final Logger log = LoggerFactory.getLogger(CandidateQuizModelController.class);

    @Inject
    private CandidateQuizModelFacade candidateQuizModelFacade;

    /**
     * POST : Create a new candidateQuizModel.
     *
     * @param candidateQuizModel the candidateQuizModel to create
     * @return the Response with status 201 (Created) and with body the new
     * candidateQuizModel, or with status 400 (Bad Request) if the
     * candidateQuizModel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createCandidateQuizModel(CandidateQuizModel candidateQuizModel) throws URISyntaxException {
        log.debug("REST request to save CandidateQuizModel : {}", candidateQuizModel);
        candidateQuizModelFacade.create(candidateQuizModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate-quiz-model/" + candidateQuizModel.getId())),
                "candidateQuizModel", candidateQuizModel.getId().toString())
                .entity(candidateQuizModel).build();
    }

    /**
     * PUT : Updates an existing candidateQuizModel.
     *
     * @param candidateQuizModel the candidateQuizModel to update
     * @return the Response with status 200 (OK) and with body the updated
     * candidateQuizModel, or with status 400 (Bad Request) if the
     * candidateQuizModel is not valid, or with status 500 (Internal Server
     * Error) if the candidateQuizModel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateCandidateQuizModel(CandidateQuizModel candidateQuizModel) throws URISyntaxException {
        log.debug("REST request to update CandidateQuizModel : {}", candidateQuizModel);
        candidateQuizModelFacade.edit(candidateQuizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateQuizModel", candidateQuizModel.getId().toString())
                .entity(candidateQuizModel).build();
    }

    /**
     * GET : get all the candidateQuizModels. <% if (pagination != 'no') {}
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of
     * candidateQuizModels in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<CandidateQuizModel> getAllCandidateQuizModels() {
        log.debug("REST request to get all CandidateQuizModels");
        List<CandidateQuizModel> candidateQuizModels = candidateQuizModelFacade.findAll();
        return candidateQuizModels;
    }

    /**
     * GET /:id : get the "id" candidateQuizModel.
     *
     * @param id the id of the candidateQuizModel to retrieve
     * @return the Response with status 200 (OK) and with body the
     * candidateQuizModel, or with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getCandidateQuizModel(@PathParam("id") Long id) {
        log.debug("REST request to get CandidateQuizModel : {}", id);
        CandidateQuizModel candidateQuizModel = candidateQuizModelFacade.find(id);
        return Optional.ofNullable(candidateQuizModel)
                .map(result -> Response.status(Response.Status.OK).entity(candidateQuizModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" candidateQuizModel.
     *
     * @param id the id of the candidateQuizModel to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeCandidateQuizModel(@PathParam("id") Long id) {
        log.debug("REST request to delete CandidateQuizModel : {}", id);
        candidateQuizModelFacade.remove(candidateQuizModelFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateQuizModel", id.toString()).build();
    }

}
