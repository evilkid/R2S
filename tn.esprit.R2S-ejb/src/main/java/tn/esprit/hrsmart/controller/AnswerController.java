package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.Answer;
import tn.esprit.hrsmart.service.AnswerFacade;
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
 * REST controller for managing Answer.
 */
@Path("/api/answer")
public class AnswerController {

    private final Logger log = LoggerFactory.getLogger(AnswerController.class);

    @Inject
    private AnswerFacade answerFacade;

    /**
     * POST : Create a new answer.
     *
     * @param answer the answer to create
     * @return the Response with status 201 (Created) and with body the new
     * answer, or with status 400 (Bad Request) if the answer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createAnswer(Answer answer) throws URISyntaxException {
        log.debug("REST request to save Answer : {}", answer);
        answerFacade.create(answer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/answer/" + answer.getId())),
                "answer", answer.getId().toString())
                .entity(answer).build();
    }

    /**
     * PUT : Updates an existing answer.
     *
     * @param answer the answer to update
     * @return the Response with status 200 (OK) and with body the updated
     * answer, or with status 400 (Bad Request) if the answer is not valid, or
     * with status 500 (Internal Server Error) if the answer couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateAnswer(Answer answer) throws URISyntaxException {
        log.debug("REST request to update Answer : {}", answer);
        answerFacade.edit(answer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "answer", answer.getId().toString())
                .entity(answer).build();
    }

    /**
     * GET : get all the answers. <% if (pagination != 'no') {} @param pageable
     * the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of answers in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Answer> getAllAnswers() {
        log.debug("REST request to get all Answers");
        List<Answer> answers = answerFacade.findAll();
        return answers;
    }

    /**
     * GET /:id : get the "id" answer.
     *
     * @param id the id of the answer to retrieve
     * @return the Response with status 200 (OK) and with body the answer, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getAnswer(@PathParam("id") Long id) {
        log.debug("REST request to get Answer : {}", id);
        Answer answer = answerFacade.find(id);
        return Optional.ofNullable(answer)
                .map(result -> Response.status(Response.Status.OK).entity(answer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" answer.
     *
     * @param id the id of the answer to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeAnswer(@PathParam("id") Long id) {
        log.debug("REST request to delete Answer : {}", id);
        answerFacade.remove(answerFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "answer", id.toString()).build();
    }

}
