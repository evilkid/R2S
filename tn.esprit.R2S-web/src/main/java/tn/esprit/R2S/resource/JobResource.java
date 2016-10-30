package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IJobFieldService;
import tn.esprit.R2S.interfaces.IJobService;
import tn.esprit.R2S.interfaces.INotificationService;
import tn.esprit.R2S.interfaces.IRewardService;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.model.JobField;
import tn.esprit.R2S.model.Notification;
import tn.esprit.R2S.model.Reward;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/job")
@Secured(Roles.RECRUITMENT_MANAGER)
public class JobResource {

    @EJB
    private IJobService jobService;

    @EJB
    private IRewardService rewardService;

    @EJB
    private INotificationService notificationService;

    @EJB
    private IJobFieldService jobFieldService;

    @POST
    public Response createJob(Job job) throws URISyntaxException {
        System.out.println("job " + job);
        jobService.create(job);
        return Response.created(new URI("/resources/api/job/" + job.getId())).entity(job).build();
    }

    @Path("/{id}/reward")
    @POST
    public Response addReward(Reward reward, @PathParam("id") Long id) {
        return Optional.ofNullable(jobService.find(id))
                .map(job -> {
                    reward.setJob(job);
                    rewardService.create(reward);
                    return Response.status(Response.Status.CREATED).entity(reward).build();
                })
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{id}/notify")
    @POST
    public Response addNotification(Notification notification, @PathParam("id") Long id) {
        return Optional.ofNullable(jobService.find(id))
                .map(job -> {
                    notification.setJob(job);
                    notificationService.create(notification);
                    return Response.status(Response.Status.CREATED).entity(notification).build();
                })
                .orElseThrow(NotFoundException::new);
    }

    @PUT
    public Response updateJob(Job job) {
        jobService.edit(job);
        return Response.ok(job).build();
    }

    @GET
    public List<Job> getAllJobs() {
        return jobService.findAll();
    }

    @Path("/{id}")
    @GET
    public Response getJob(@PathParam("id") Long id) {

        return Optional.ofNullable(jobService.find(id))
                .map(job -> Response.ok(job).build())
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{id}")
    @DELETE
    public Response removeJob(@PathParam("id") Long id) {
        return Optional.ofNullable(jobService.find(id))
                .map(job -> {
                    jobService.remove(job);
                    return Response.ok().build();
                }).orElseThrow(NotFoundException::new);
    }

    @GET
    @Path("form")
    public List<JobField> getJobField() {
        return jobFieldService.findAll();
    }
}
