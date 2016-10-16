package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Question;
import tn.esprit.R2S.service.QuestionFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/question")
public class QuestionController {

    @Inject
    private QuestionFacade questionFacade;

    @POST
    public Response createQuestion(Question question) throws URISyntaxException {
        questionFacade.create(question);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/question/" + question.getId())),
                "question", question.getId().toString())
                .entity(question).build();
    }

    @PUT
    public Response updateQuestion(Question question) throws URISyntaxException {
        questionFacade.edit(question);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "question", question.getId().toString())
                .entity(question).build();
    }

    @GET
    public List<Question> getAllQuestions() {
        List<Question> questions = questionFacade.findAll();
        return questions;
    }

    @Path("/{id}")
    @GET
    public Response getQuestion(@PathParam("id") Long id) {
        Question question = questionFacade.find(id);
        return Optional.ofNullable(question)
                .map(result -> Response.status(Response.Status.OK).entity(question).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeQuestion(@PathParam("id") Long id) {
        questionFacade.remove(questionFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "question", id.toString()).build();
    }

}
