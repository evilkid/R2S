package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateQuizModelService;
import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.interfaces.IQuestionService;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.CandidateQuizModel;
import tn.esprit.R2S.model.Question;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/api/candidate-quiz-model")

public class CandidateQuizModelResource {


    @EJB
    private ICandidateQuizModelService candidateQuizModelService;
    @EJB
    private IQuestionService questionService;
    @EJB
    private ICandidateService candidateService;

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

    @GET
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

    @Path("/{id}/score")
    @GET
    public double calculateScore(@PathParam("id") Long id) {
        CandidateQuizModel candidateQuizModel = candidateQuizModelService.find(id);
        return candidateQuizModelService.calculateScore(candidateQuizModel);
    }

    @Path("/{idCandidateQuiz}/score/{idQuestion}")
    @GET
    public double getSingleQuestionNote(@PathParam("idCandidateQuiz") Long idCandidateQuizModel,
                                        @PathParam("idQuestion") Long idQuestion) {
        CandidateQuizModel candidateQuizModel = candidateQuizModelService.find(idCandidateQuizModel);
        Question question = questionService.find(idQuestion);
        return candidateQuizModelService.calculateSingleQuestionNote(candidateQuizModel, question);
    }

    @Path("/historique")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<CandidateQuizModel, Double> getHistorique() {
        return candidateQuizModelService.getHistorique();
    }

    @GET
    @Path("/{candidateId}/{minScore}")
    public List<CandidateQuizModel> getByCandidate(long candidateId, double minScore){


        Candidate candidate=candidateService.find(candidateId);
        if (candidate!=null)
            return candidateQuizModelService.getByCandidate(candidate,minScore);

        return null;
    }
}
