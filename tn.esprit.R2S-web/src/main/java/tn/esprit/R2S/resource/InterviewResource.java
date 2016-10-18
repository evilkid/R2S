package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Interview;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.InterviewService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Interview.
 */
@Path("/api/interview")

public class InterviewResource {


    @EJB
    private InterviewService interviewService;

    /**
     * POST : Create a new interview.
     *
     * @param interview the interview to create
     * @return the Response with status 201 (Created) and with body the new
     * interview, or with status 400 (Bad Request) if the interview has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createInterview(Interview interview) throws URISyntaxException {

        interviewService.create(interview);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/interview/" + interview.getId())),
                "interview", interview.getId().toString())
                .entity(interview).build();
    }

    /**
     * PUT : Updates an existing interview.
     *
     * @param interview the interview to update
     * @return the Response with status 200 (OK) and with body the updated
     * interview, or with status 400 (Bad Request) if the interview is not
     * valid, or with status 500 (Internal Server Error) if the interview
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateInterview(Interview interview) throws URISyntaxException {

        interviewService.edit(interview);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "interview", interview.getId().toString())
                .entity(interview).build();
    }

    /**
     * GET : get all the interviews. <% if (pagination != 'no') {} @param
     * pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of interviews in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Interview> getAllInterviews() {

        List<Interview> interviews = interviewService.findAll();
        return interviews;
    }

    /**
     * GET /:id : get the "id" interview.
     *
     * @param id the id of the interview to retrieve
     * @return the Response with status 200 (OK) and with body the interview, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getInterview(@PathParam("id") Long id) {

        Interview interview = interviewService.find(id);
        return Optional.ofNullable(interview)
                .map(result -> Response.status(Response.Status.OK).entity(interview).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" interview.
     *
     * @param id the id of the interview to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeInterview(@PathParam("id") Long id) {

        interviewService.remove(interviewService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "interview", id.toString()).build();
    }

}
