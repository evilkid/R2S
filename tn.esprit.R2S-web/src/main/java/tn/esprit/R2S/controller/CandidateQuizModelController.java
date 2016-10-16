package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.CandidateQuizModel;
import tn.esprit.R2S.service.CandidateQuizModelFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/candidate-quiz-model")
public class CandidateQuizModelController {

    @Inject
    private CandidateQuizModelFacade candidateQuizModelFacade;

    @POST
    public Response createCandidateQuizModel(CandidateQuizModel candidateQuizModel) throws URISyntaxException {
        candidateQuizModelFacade.create(candidateQuizModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate-quiz-model/" + candidateQuizModel.getId())),
                "candidateQuizModel", candidateQuizModel.getId().toString())
                .entity(candidateQuizModel).build();
    }

    @PUT
    public Response updateCandidateQuizModel(CandidateQuizModel candidateQuizModel) throws URISyntaxException {
        candidateQuizModelFacade.edit(candidateQuizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateQuizModel", candidateQuizModel.getId().toString())
                .entity(candidateQuizModel).build();
    }

    @GET
    public List<CandidateQuizModel> getAllCandidateQuizModels() {
        List<CandidateQuizModel> candidateQuizModels = candidateQuizModelFacade.findAll();
        return candidateQuizModels;
    }

    @Path("/{id}")
    @GET
    public Response getCandidateQuizModel(@PathParam("id") Long id) {
        CandidateQuizModel candidateQuizModel = candidateQuizModelFacade.find(id);
        return Optional.ofNullable(candidateQuizModel)
                .map(result -> Response.status(Response.Status.OK).entity(candidateQuizModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCandidateQuizModel(@PathParam("id") Long id) {
        candidateQuizModelFacade.remove(candidateQuizModelFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateQuizModel", id.toString()).build();
    }

}
