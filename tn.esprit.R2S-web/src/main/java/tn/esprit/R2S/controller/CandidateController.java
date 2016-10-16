package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.service.CandidateFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/candidate")
public class CandidateController {

    @Inject
    private CandidateFacade candidateFacade;

    @POST
    public Response createCandidate(Candidate candidate) throws URISyntaxException {
        candidateFacade.create(candidate);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate/" + candidate.getCin())),
                "candidate", candidate.getCin().toString())
                .entity(candidate).build();
    }

    @PUT
    public Response updateCandidate(Candidate candidate) throws URISyntaxException {
        candidateFacade.edit(candidate);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidate", candidate.getCin().toString())
                .entity(candidate).build();
    }

    @GET
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = candidateFacade.findAll();
        return candidates;
    }

    @Path("/{cin}")
    @GET
    public Response getCandidate(@PathParam("cin") Long cin) {
        Candidate candidate = candidateFacade.find(cin);
        return Optional.ofNullable(candidate)
                .map(result -> Response.status(Response.Status.OK).entity(candidate).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{cin}")
    @DELETE
    public Response removeCandidate(@PathParam("cin") Long cin) {
        candidateFacade.remove(candidateFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidate", cin.toString()).build();
    }

}
