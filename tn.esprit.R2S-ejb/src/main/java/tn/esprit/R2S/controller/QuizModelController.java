package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.QuizModel;
import tn.esprit.R2S.service.QuizModelFacade;
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
 * REST controller for managing QuizModel.
 */
@Path("/api/quiz-model")
public class QuizModelController {

    private final Logger log = LoggerFactory.getLogger(QuizModelController.class);

    @Inject
    private QuizModelFacade quizModelFacade;

    /**
     * POST : Create a new quizModel.
     *
     * @param quizModel the quizModel to create
     * @return the Response with status 201 (Created) and with body the new
     * quizModel, or with status 400 (Bad Request) if the quizModel has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createQuizModel(QuizModel quizModel) throws URISyntaxException {
        log.debug("REST request to save QuizModel : {}", quizModel);
        quizModelFacade.create(quizModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/quiz-model/" + quizModel.getId())),
                "quizModel", quizModel.getId().toString())
                .entity(quizModel).build();
    }

    /**
     * PUT : Updates an existing quizModel.
     *
     * @param quizModel the quizModel to update
     * @return the Response with status 200 (OK) and with body the updated
     * quizModel, or with status 400 (Bad Request) if the quizModel is not
     * valid, or with status 500 (Internal Server Error) if the quizModel
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateQuizModel(QuizModel quizModel) throws URISyntaxException {
        log.debug("REST request to update QuizModel : {}", quizModel);
        quizModelFacade.edit(quizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "quizModel", quizModel.getId().toString())
                .entity(quizModel).build();
    }

    /**
     * GET : get all the quizModels. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of quizModels in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<QuizModel> getAllQuizModels() {
        log.debug("REST request to get all QuizModels");
        List<QuizModel> quizModels = quizModelFacade.findAll();
        return quizModels;
    }

    /**
     * GET /:id : get the "id" quizModel.
     *
     * @param id the id of the quizModel to retrieve
     * @return the Response with status 200 (OK) and with body the quizModel, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getQuizModel(@PathParam("id") Long id) {
        log.debug("REST request to get QuizModel : {}", id);
        QuizModel quizModel = quizModelFacade.find(id);
        return Optional.ofNullable(quizModel)
                .map(result -> Response.status(Response.Status.OK).entity(quizModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" quizModel.
     *
     * @param id the id of the quizModel to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeQuizModel(@PathParam("id") Long id) {
        log.debug("REST request to delete QuizModel : {}", id);
        quizModelFacade.remove(quizModelFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "quizModel", id.toString()).build();
    }

}
