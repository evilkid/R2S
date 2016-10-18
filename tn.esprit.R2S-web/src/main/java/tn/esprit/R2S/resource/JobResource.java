package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.JobService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Job.
 */
@Path("/api/job")

public class JobResource {


    @EJB
    private JobService jobService;

    /**
     * POST : Create a new job.
     *
     * @param job the job to create
     * @return the Response with status 201 (Created) and with body the new job,
     * or with status 400 (Bad Request) if the job has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createJob(Job job) throws URISyntaxException {

        jobService.create(job);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/job/" + job.getId())),
                "job", job.getId().toString())
                .entity(job).build();
    }

    /**
     * PUT : Updates an existing job.
     *
     * @param job the job to update
     * @return the Response with status 200 (OK) and with body the updated job,
     * or with status 400 (Bad Request) if the job is not valid, or with status
     * 500 (Internal Server Error) if the job couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateJob(Job job) throws URISyntaxException {

        jobService.edit(job);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "job", job.getId().toString())
                .entity(job).build();
    }

    /**
     * GET : get all the jobs. <% if (pagination != 'no') {} @param pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of jobs in body<%
     * if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Job> getAllJobs() {

        List<Job> jobs = jobService.findAll();
        return jobs;
    }

    /**
     * GET /:id : get the "id" job.
     *
     * @param id the id of the job to retrieve
     * @return the Response with status 200 (OK) and with body the job, or with
     * status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getJob(@PathParam("id") Long id) {

        Job job = jobService.find(id);
        return Optional.ofNullable(job)
                .map(result -> Response.status(Response.Status.OK).entity(job).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" job.
     *
     * @param id the id of the job to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeJob(@PathParam("id") Long id) {

        jobService.remove(jobService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "job", id.toString()).build();
    }

}
