package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IAnswerService;
import tn.esprit.R2S.model.Answer;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.AnswerService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/answer")
@RequestScoped
public class AnswerResource {

    @EJB
    private IAnswerService answerService;

    @POST
    public Response createAnswer(Answer answer) throws URISyntaxException {

        answerService.create(answer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/answer/" + answer.getId())),
                "answer", answer.getId().toString())
                .entity(answer).build();
    }

    @PUT
    public Response updateAnswer(Answer answer) throws URISyntaxException {

        answerService.edit(answer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "answer", answer.getId().toString())
                .entity(answer).build();
    }
    @GET
    public List<Answer> getAllAnswers() {

        List<Answer> answers = answerService.findAll();
        return answers;
    }

    @Path("/{id}")
    @GET
    public Response getAnswer(@PathParam("id") Long id) {

        Answer answer = answerService.find(id);
        return Optional.ofNullable(answer)
                .map(result -> Response.status(Response.Status.OK).entity(answer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeAnswer(@PathParam("id") Long id) {

        answerService.remove(answerService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "answer", id.toString()).build();
    }

}
