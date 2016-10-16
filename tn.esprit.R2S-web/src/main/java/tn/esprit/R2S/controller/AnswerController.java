package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Answer;
import tn.esprit.R2S.service.AnswerFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Path("/api/answer")
public class AnswerController {

    @Inject
    private AnswerFacade answerFacade;

    @POST
    public Response createAnswer(Answer answer) throws URISyntaxException {
        answerFacade.create(answer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/answer/" + answer.getId())),
                "answer", answer.getId().toString())
                .entity(answer).build();
    }

    @PUT
    public Response updateAnswer(Answer answer) throws URISyntaxException {
        answerFacade.edit(answer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "answer", answer.getId().toString())
                .entity(answer).build();
    }

    @GET
    public List<Answer> getAllAnswers() {
        List<Answer> answers = answerFacade.findAll();
        return answers;
    }

    @Path("/{id}")
    @GET
    public Response getAnswer(@PathParam("id") Long id) {
        Answer answer = answerFacade.find(id);
        return Optional.ofNullable(answer)
                .map(result -> Response.status(Response.Status.OK).entity(answer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeAnswer(@PathParam("id") Long id) {
        answerFacade.remove(answerFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "answer", id.toString()).build();
    }

}
