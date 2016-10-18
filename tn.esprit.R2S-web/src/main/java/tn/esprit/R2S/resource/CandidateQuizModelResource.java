package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateQuizModelService;
import tn.esprit.R2S.model.CandidateQuizModel;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/candidate-quiz-model")

public class CandidateQuizModelResource {


    @EJB
    private ICandidateQuizModelService candidateQuizModelService;

    @POST
    public Response createCandidateQuizModel(CandidateQuizModel candidateQuizModel) throws URISyntaxException {

        candidateQuizModelService.create(candidateQuizModel);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate-quiz-model/" + candidateQuizModel.getId())),
                "candidateQuizModel", candidateQuizModel.getId().toString())
                .entity(candidateQuizModel).build();
    }

    @PUT
    public Response updateCandidateQuizModel(CandidateQuizModel candidateQuizModel) throws URISyntaxException {

        candidateQuizModelService.edit(candidateQuizModel);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidateQuizModel", candidateQuizModel.getId().toString())
                .entity(candidateQuizModel).build();
    }


    public List<CandidateQuizModel> getAllCandidateQuizModels() {

        List<CandidateQuizModel> candidateQuizModels = candidateQuizModelService.findAll();
        return candidateQuizModels;
    }


    @Path("/{id}")
    @GET
    public Response getCandidateQuizModel(@PathParam("id") Long id) {

        CandidateQuizModel candidateQuizModel = candidateQuizModelService.find(id);
        return Optional.ofNullable(candidateQuizModel)
                .map(result -> Response.status(Response.Status.OK).entity(candidateQuizModel).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    @Path("/{id}")
    @DELETE
    public Response removeCandidateQuizModel(@PathParam("id") Long id) {

        candidateQuizModelService.remove(candidateQuizModelService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidateQuizModel", id.toString()).build();
    }

}
