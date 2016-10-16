package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.CandidateAnswer;
import tn.esprit.R2S.service.CandidateAnswerFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Path("/api/candidate-answer")
public class CandidateAnswerController {

    @Inject
    private CandidateAnswerFacade candidateAnswerFacade;

    @POST
    public Response createCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {
        candidateAnswerFacade.create(candidateAnswer);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate-answer/" + candidateAnswer.getId())),
                "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }

    @PUT
    public Response updateCandidateAnswer(CandidateAnswer candidateAnswer) throws URISyntaxException {
        candidateAnswerFacade.edit(candidateAnswer);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateAnswer", candidateAnswer.getId().toString())
                .entity(candidateAnswer).build();
    }

    @GET
    public List<CandidateAnswer> getAllCandidateAnswers() {
        List<CandidateAnswer> candidateAnswers = candidateAnswerFacade.findAll();
        return candidateAnswers;
    }

    @Path("/{id}")
    @GET
    public Response getCandidateAnswer(@PathParam("id") Long id) {
        CandidateAnswer candidateAnswer = candidateAnswerFacade.find(id);
        return Optional.ofNullable(candidateAnswer)
                .map(result -> Response.status(Response.Status.OK).entity(candidateAnswer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCandidateAnswer(@PathParam("id") Long id) {
        candidateAnswerFacade.remove(candidateAnswerFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateAnswer", id.toString()).build();
    }

}
