package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/candidate")
public class CandidateResource {


    @EJB
    private ICandidateService candidateService;

    @POST
    public Response createCandidate(Candidate candidate) throws URISyntaxException {

        candidateService.create(candidate);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate/" + candidate.getCin())),
                "candidate", candidate.getCin().toString())
                .entity(candidate).build();
    }

    @PUT
    public Response updateCandidate(Candidate candidate) throws URISyntaxException {

        candidateService.edit(candidate);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidate", candidate.getCin().toString())
                .entity(candidate).build();
    }


    @GET
    public List<Candidate> getAllCandidates() {

        List<Candidate> candidates = candidateService.findAll();
        return candidates;
    }


    @Path("/{cin}")
    @GET
    public Response getCandidate(@PathParam("cin") Long cin) {

        Candidate candidate = candidateService.find(cin);
        return Optional.ofNullable(candidate)
                .map(result -> Response.status(Response.Status.OK).entity(candidate).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


    @Path("/{cin}")
    @DELETE
    public Response removeCandidate(@PathParam("cin") Long cin) {

        candidateService.remove(candidateService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidate", cin.toString()).build();
    }

}
