package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.QuizModel;
import tn.esprit.R2S.service.QuizModelFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/quiz-model")
public class QuizModelController {

    @Inject
    private QuizModelFacade quizModelFacade;

    @POST
    public Response createQuizModel(QuizModel quizModel) throws URISyntaxException {
        quizModelFacade.create(quizModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/quiz-model/" + quizModel.getId())),
                "quizModel", quizModel.getId().toString())
                .entity(quizModel).build();
    }

    @PUT
    public Response updateQuizModel(QuizModel quizModel) throws URISyntaxException {
        quizModelFacade.edit(quizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "quizModel", quizModel.getId().toString())
                .entity(quizModel).build();
    }

    @GET
    public List<QuizModel> getAllQuizModels() {
        List<QuizModel> quizModels = quizModelFacade.findAll();
        return quizModels;
    }

    @Path("/{id}")
    @GET
    public Response getQuizModel(@PathParam("id") Long id) {
        QuizModel quizModel = quizModelFacade.find(id);
        return Optional.ofNullable(quizModel)
                .map(result -> Response.status(Response.Status.OK).entity(quizModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeQuizModel(@PathParam("id") Long id) {
        quizModelFacade.remove(quizModelFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "quizModel", id.toString()).build();
    }

}
