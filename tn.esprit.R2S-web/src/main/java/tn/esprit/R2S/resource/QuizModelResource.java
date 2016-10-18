package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IQuizModelService;
import tn.esprit.R2S.model.QuizModel;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/quiz-model")
public class QuizModelResource {


    @EJB
    private IQuizModelService quizModelService;

    @POST
    public Response createQuizModel(QuizModel quizModel) throws URISyntaxException {

        quizModelService.create(quizModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/quiz-model/" + quizModel.getId())),
                "quizModel", quizModel.getId().toString())
                .entity(quizModel).build();
    }
    @PUT
    public Response updateQuizModel(QuizModel quizModel) throws URISyntaxException {

        quizModelService.edit(quizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "quizModel", quizModel.getId().toString())
                .entity(quizModel).build();
    }

    @GET
    public List<QuizModel> getAllQuizModels() {

        List<QuizModel> quizModels = quizModelService.findAll();
        return quizModels;
    }
    @Path("/{id}")
    @GET
    public Response getQuizModel(@PathParam("id") Long id) {

        QuizModel quizModel = quizModelService.find(id);
        return Optional.ofNullable(quizModel)
                .map(result -> Response.status(Response.Status.OK).entity(quizModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeQuizModel(@PathParam("id") Long id) {

        quizModelService.remove(quizModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "quizModel", id.toString()).build();
    }

}
