package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.CandidateQuizModel;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.CandidateQuizModelService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CandidateQuizModel.
 */
@Path("/api/candidate-quiz-model")

public class CandidateQuizModelResource {


    @EJB
    private CandidateQuizModelService candidateQuizModelService;

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

        candidateQuizModelService.create(candidateQuizModel);
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

        candidateQuizModelService.edit(candidateQuizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateQuizModel", candidateQuizModel.getId().toString())
                .entity(candidateQuizModel).build();
    }

    /**
     * GET : get all the candidateQuizModels. <% if (pagination != 'no') {}
     *
     * @param pageable the p
     *                 <p>
     *                 agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     *                 filter the filter of the r
     *                 equest<% }}
     * @return the Response with status 200 (OK) and the list of
     * candidateQuizModels in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<CandidateQuizModel> getAllCandidateQuizModels() {

        List<CandidateQuizModel> candidateQuizModels = candidateQuizModelService.findAll();
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

        CandidateQuizModel candidateQuizModel = candidateQuizModelService.find(id);
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

        candidateQuizModelService.remove(candidateQuizModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateQuizModel", id.toString()).build();
    }

}
