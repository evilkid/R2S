package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateAnswerService;
import tn.esprit.R2S.model.CandidateAnswer;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/candidate-answer")
public class CandidateAnswerResource implements Serializable {

    @EJB
    private ICandidateAnswerService candidateAnswerService;

    @POST
    public Response createCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {

        candidateAnswerService.create(candidateAnswer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate-answer/" + candidateAnswer.getId())),
                "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }


    @PUT
    public Response updateCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {

        candidateAnswerService.edit(candidateAnswer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }


    @GET
    public List<CandidateAnswer> getAllCandidateAnswers() {
        System.out.println("CANDID:" + candidateAnswerService);
        return candidateAnswerService.findAll();
    }


    @Path("/{id}")
    @GET
    public Response getCandidateAnswer(@PathParam("id") Long id) {

        CandidateAnswer candidateAnswer = candidateAnswerService.find(id);
        return Optional.ofNullable(candidateAnswer)
                .map(result -> Response.status(Response.Status.OK).entity(candidateAnswer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCandidateAnswer(@PathParam("id") Long id) {

        candidateAnswerService.remove(candidateAnswerService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateAnswer", id.toString()).build();
    }

}
