package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IQuestionService;
import tn.esprit.R2S.model.Question;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/question")
public class QuestionResource {

    @EJB
    private IQuestionService questionService;

    @POST
    public Response createQuestion(Question question) throws URISyntaxException {

        questionService.create(question);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/question/" + question.getId())),
                "question", question.getId().toString())
                .entity(question).build();
    }

    @PUT
    public Response updateQuestion(Question question) throws URISyntaxException {

        questionService.edit(question);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "question", question.getId().toString())
                .entity(question).build();
    }

    @GET
    public List<Question> getAllQuestions() {

        List<Question> questions = questionService.findAll();
        return questions;
    }

    @Path("/{id}")
    @GET
    public Response getQuestion(@PathParam("id") Long id) {

        Question question = questionService.find(id);
        return Optional.ofNullable(question)
                .map(result -> Response.status(Response.Status.OK).entity(question).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeQuestion(@PathParam("id") Long id) {

        questionService.remove(questionService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "question", id.toString()).build();
    }

}
