package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.Candidate;
import tn.esprit.hrsmart.service.CandidateFacade;
import tn.esprit.hrsmart.controller.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST controller for managing Candidate.
 */
@Path("/api/candidate")
public class CandidateController {

    private final Logger log = LoggerFactory.getLogger(CandidateController.class);

    @Inject
    private CandidateFacade candidateFacade;

    /**
     * POST : Create a new candidate.
     *
     * @param candidate the candidate to create
     * @return the Response with status 201 (Created) and with body the new
     * candidate, or with status 400 (Bad Request) if the candidate has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createCandidate(Candidate candidate) throws URISyntaxException {
        log.debug("REST request to save Candidate : {}", candidate);
        candidateFacade.create(candidate);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate/" + candidate.getCin())),
                "candidate", candidate.getCin().toString())
                .entity(candidate).build();
    }

    /**
     * PUT : Updates an existing candidate.
     *
     * @param candidate the candidate to update
     * @return the Response with status 200 (OK) and with body the updated
     * candidate, or with status 400 (Bad Request) if the candidate is not
     * valid, or with status 500 (Internal Server Error) if the candidate
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateCandidate(Candidate candidate) throws URISyntaxException {
        log.debug("REST request to update Candidate : {}", candidate);
        candidateFacade.edit(candidate);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "candidate", candidate.getCin().toString())
                .entity(candidate).build();
    }

    /**
     * GET : get all the candidates. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of candidates in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Candidate> getAllCandidates() {
        log.debug("REST request to get all Candidates");
        List<Candidate> candidates = candidateFacade.findAll();
        return candidates;
    }

    /**
     * GET /:cin : get the "cin" candidate.
     *
     * @param cin the cin of the candidate to retrieve
     * @return the Response with status 200 (OK) and with body the candidate, or
     * with status 404 (Not Found)
     */
    @Path("/{cin}")
    @GET
    public Response getCandidate(@PathParam("cin") Long cin) {
        log.debug("REST request to get Candidate : {}", cin);
        Candidate candidate = candidateFacade.find(cin);
        return Optional.ofNullable(candidate)
                .map(result -> Response.status(Response.Status.OK).entity(candidate).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:cin : remove the "cin" candidate.
     *
     * @param cin the cin of the candidate to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{cin}")
    @DELETE
    public Response removeCandidate(@PathParam("cin") Long cin) {
        log.debug("REST request to delete Candidate : {}", cin);
        candidateFacade.remove(candidateFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "candidate", cin.toString()).build();
    }

}
