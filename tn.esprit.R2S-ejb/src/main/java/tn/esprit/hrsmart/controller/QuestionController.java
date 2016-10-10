package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.Question;
import tn.esprit.hrsmart.service.QuestionFacade;
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
 * REST controller for managing Question.
 */
@Path("/api/question")
public class QuestionController {

    private final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Inject
    private QuestionFacade questionFacade;

    /**
     * POST : Create a new question.
     *
     * @param question the question to create
     * @return the Response with status 201 (Created) and with body the new
     * question, or with status 400 (Bad Request) if the question has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createQuestion(Question question) throws URISyntaxException {
        log.debug("REST request to save Question : {}", question);
        questionFacade.create(question);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/question/" + question.getId())),
                "question", question.getId().toString())
                .entity(question).build();
    }

    /**
     * PUT : Updates an existing question.
     *
     * @param question the question to update
     * @return the Response with status 200 (OK) and with body the updated
     * question, or with status 400 (Bad Request) if the question is not valid,
     * or with status 500 (Internal Server Error) if the question couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateQuestion(Question question) throws URISyntaxException {
        log.debug("REST request to update Question : {}", question);
        questionFacade.edit(question);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "question", question.getId().toString())
                .entity(question).build();
    }

    /**
     * GET : get all the questions. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of questions in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Question> getAllQuestions() {
        log.debug("REST request to get all Questions");
        List<Question> questions = questionFacade.findAll();
        return questions;
    }

    /**
     * GET /:id : get the "id" question.
     *
     * @param id the id of the question to retrieve
     * @return the Response with status 200 (OK) and with body the question, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getQuestion(@PathParam("id") Long id) {
        log.debug("REST request to get Question : {}", id);
        Question question = questionFacade.find(id);
        return Optional.ofNullable(question)
                .map(result -> Response.status(Response.Status.OK).entity(question).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" question.
     *
     * @param id the id of the question to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeQuestion(@PathParam("id") Long id) {
        log.debug("REST request to delete Question : {}", id);
        questionFacade.remove(questionFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "question", id.toString()).build();
    }

}
