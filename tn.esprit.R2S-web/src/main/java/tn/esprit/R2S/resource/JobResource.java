package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IJobService;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/job")
public class JobResource {

    @EJB
    private IJobService jobService;

    @POST
    public Response createJob(Job job) throws URISyntaxException {

        jobService.create(job);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/job/" + job.getId())),
                "job", job.getId().toString())
                .entity(job).build();
    }

    @PUT
    public Response updateJob(Job job) throws URISyntaxException {

        jobService.edit(job);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "job", job.getId().toString())
                .entity(job).build();
    }

    @GET
    public List<Job> getAllJobs() {

        List<Job> jobs = jobService.findAll();
        return jobs;
    }

    @Path("/{id}")
    @GET
    public Response getJob(@PathParam("id") Long id) {

        Job job = jobService.find(id);
        return Optional.ofNullable(job)
                .map(result -> Response.status(Response.Status.OK).entity(job).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeJob(@PathParam("id") Long id) {

        jobService.remove(jobService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "job", id.toString()).build();
    }

}
