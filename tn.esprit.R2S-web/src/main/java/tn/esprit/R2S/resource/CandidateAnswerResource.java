package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.CandidateAnswer;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.CandidateAnswerService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CandidateAnswer.
 */
@Path("/api/candidate-answer")

public class CandidateAnswerResource {


    @EJB
    private CandidateAnswerService candidateAnswerService;

    /**
     * POST : Create a new candidateAnswer.
     *
     * @param candidateAnswer the candidateAnswer to create
     * @return the Response with status 201 (Created) and with body the new
     * candidateAnswer, or with status 400 (Bad Request) if the candidateAnswer
     * has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {

        candidateAnswerService.create(candidateAnswer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate-answer/" + candidateAnswer.getId())),
                "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }

    /**
     * PUT : Updates an existing candidateAnswer.
     *
     * @param candidateAnswer the candidateAnswer to update
     * @return the Response with status 200 (OK) and with body the updated
     * candidateAnswer, or with status 400 (Bad Request) if the candidateAnswer
     * is not valid, or with status 500 (Internal Server Error) if the
     * candidateAnswer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {

        candidateAnswerService.edit(candidateAnswer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }

    /**
     * GET : get all the candidateAnswers. <% if (pagination != 'no') {} @param
     * pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of
     * candidateAnswers in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<CandidateAnswer> getAllCandidateAnswers() {

        List<CandidateAnswer> candidateAnswers = candidateAnswerService.findAll();
        return candidateAnswers;
    }

    /**
     * GET /:id : get the "id" candidateAnswer.
     *
     * @param id the id of the candidateAnswer to retrieve
     * @return the Response with status 200 (OK) and with body the
     * candidateAnswer, or with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getCandidateAnswer(@PathParam("id") Long id) {

        CandidateAnswer candidateAnswer = candidateAnswerService.find(id);
        return Optional.ofNullable(candidateAnswer)
                .map(result -> Response.status(Response.Status.OK).entity(candidateAnswer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" candidateAnswer.
     *
     * @param id the id of the candidateAnswer to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeCandidateAnswer(@PathParam("id") Long id) {

        candidateAnswerService.remove(candidateAnswerService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateAnswer", id.toString()).build();
    }

}
