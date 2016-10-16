package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.service.JobFacade;
import tn.esprit.R2S.controller.util.HeaderUtil;
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

@Path("/api/job")
public class JobController {

    @Inject
    private JobFacade jobFacade;

    @POST
    public Response createJob(Job job) throws URISyntaxException {
        jobFacade.create(job);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/job/" + job.getId())),
                "job", job.getId().toString())
                .entity(job).build();
    }

    @PUT
    public Response updateJob(Job job) throws URISyntaxException {
        jobFacade.edit(job);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "job", job.getId().toString())
                .entity(job).build();
    }

    @GET
    public List<Job> getAllJobs() {
        List<Job> jobs = jobFacade.findAll();
        return jobs;
    }

    @Path("/{id}")
    @GET
    public Response getJob(@PathParam("id") Long id) {
        Job job = jobFacade.find(id);
        return Optional.ofNullable(job)
                .map(result -> Response.status(Response.Status.OK).entity(job).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeJob(@PathParam("id") Long id) {
        jobFacade.remove(jobFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "job", id.toString()).build();
    }

}
