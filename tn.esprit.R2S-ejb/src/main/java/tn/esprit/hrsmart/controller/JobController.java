package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.Job;
import tn.esprit.hrsmart.service.JobFacade;
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
 * REST controller for managing Job.
 */
@Path("/api/job")
public class JobController {

    private final Logger log = LoggerFactory.getLogger(JobController.class);

    @Inject
    private JobFacade jobFacade;

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
        log.debug("REST request to save Job : {}", job);
        jobFacade.create(job);
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
        log.debug("REST request to update Job : {}", job);
        jobFacade.edit(job);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "job", job.getId().toString())
                .entity(job).build();
    }

    /**
     * GET : get all the jobs. <% if (pagination != 'no') {} @param pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of jobs in body<%
     * if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Job> getAllJobs() {
        log.debug("REST request to get all Jobs");
        List<Job> jobs = jobFacade.findAll();
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
        log.debug("REST request to get Job : {}", id);
        Job job = jobFacade.find(id);
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
        log.debug("REST request to delete Job : {}", id);
        jobFacade.remove(jobFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "job", id.toString()).build();
    }

}
