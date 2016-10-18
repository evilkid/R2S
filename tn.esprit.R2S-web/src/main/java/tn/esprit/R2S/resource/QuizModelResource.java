package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.QuizModel;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.QuizModelService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing QuizModel.
 */
@Path("/api/quiz-model")

public class QuizModelResource {


    @EJB
    private QuizModelService quizModelService;

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

        quizModelService.create(quizModel);
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

        quizModelService.edit(quizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "quizModel", quizModel.getId().toString())
                .entity(quizModel).build();
    }

    /**
     * GET : get all the quizModels. <% if (pagination != 'no') {} @param
     * pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of quizModels in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<QuizModel> getAllQuizModels() {

        List<QuizModel> quizModels = quizModelService.findAll();
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

        QuizModel quizModel = quizModelService.find(id);
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

        quizModelService.remove(quizModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "quizModel", id.toString()).build();
    }

}
