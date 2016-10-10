package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.CandidateAnswer;
import tn.esprit.hrsmart.service.CandidateAnswerFacade;
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
 * REST controller for managing CandidateAnswer.
 */
@Path("/api/candidate-answer")
public class CandidateAnswerController {

    private final Logger log = LoggerFactory.getLogger(CandidateAnswerController.class);

    @Inject
    private CandidateAnswerFacade candidateAnswerFacade;

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
        log.debug("REST request to save CandidateAnswer : {}", candidateAnswer);
        candidateAnswerFacade.create(candidateAnswer);
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
        log.debug("REST request to update CandidateAnswer : {}", candidateAnswer);
        candidateAnswerFacade.edit(candidateAnswer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }

    /**
     * GET : get all the candidateAnswers. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of
     * candidateAnswers in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<CandidateAnswer> getAllCandidateAnswers() {
        log.debug("REST request to get all CandidateAnswers");
        List<CandidateAnswer> candidateAnswers = candidateAnswerFacade.findAll();
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
        log.debug("REST request to get CandidateAnswer : {}", id);
        CandidateAnswer candidateAnswer = candidateAnswerFacade.find(id);
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
        log.debug("REST request to delete CandidateAnswer : {}", id);
        candidateAnswerFacade.remove(candidateAnswerFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateAnswer", id.toString()).build();
    }

}
