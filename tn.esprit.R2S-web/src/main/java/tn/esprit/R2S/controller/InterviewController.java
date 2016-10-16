package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Interview;
import tn.esprit.R2S.service.InterviewFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/interview")
public class InterviewController {

    @Inject
    private InterviewFacade interviewFacade;

    @POST
    public Response createInterview(Interview interview) throws URISyntaxException {
        interviewFacade.create(interview);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/interview/" + interview.getId())),
                "interview", interview.getId().toString())
                .entity(interview).build();
    }

    @PUT
    public Response updateInterview(Interview interview) throws URISyntaxException {
        interviewFacade.edit(interview);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "interview", interview.getId().toString())
                .entity(interview).build();
    }

    @GET
    public List<Interview> getAllInterviews() {
        List<Interview> interviews = interviewFacade.findAll();
        return interviews;
    }

    @Path("/{id}")
    @GET
    public Response getInterview(@PathParam("id") Long id) {
        Interview interview = interviewFacade.find(id);
        return Optional.ofNullable(interview)
                .map(result -> Response.status(Response.Status.OK).entity(interview).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeInterview(@PathParam("id") Long id) {
        interviewFacade.remove(interviewFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "interview", id.toString()).build();
    }

}
