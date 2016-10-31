package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.*;
import tn.esprit.R2S.model.*;
import tn.esprit.R2S.util.enums.JobStatus;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/job")
//@Secured(Roles.RECRUITMENT_MANAGER)
public class JobResource {

    @EJB
    private IJobService jobService;

    @EJB
    private IRewardService rewardService;

    @EJB
    private INotificationService notificationService;

    @EJB
    private IJobFieldService jobFieldService;

    @EJB
    private IEmployeeService employeeSerivce;

    @EJB
    private IReferHashService referHashService;

    @EJB
    private ISkillService skillService;

    @POST
    public Response createJob(Job job) throws URISyntaxException {
        System.out.println("job " + job);
        jobService.create(job);
        return Response.created(new URI("/resources/api/job/" + job.getId())).entity(job).build();
    }

    //Rewards

    @Path("/{id}/reward")
    @GET
    public Response getRewards(@PathParam("id") Long id) {
        return Optional.ofNullable(jobService.findInitializeRewards(id))
                .map(job -> Response.ok(job.getRewards()).build())
                .orElseThrow(NotFoundException::new);
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

    //Skills

    @Path("/{id}/skill")
    @POST
    public Response addSkill(Skill skill, @PathParam("id") Long id) {
        Job job = jobService.find(id);
        if (job == null) {
            throw new NotFoundException("Job Not Found");
        }

        skill = skillService.findInitializeJob(skill.getId());
        if (skill == null) {
            throw new NotFoundException("Skill Not Found");
        }

        if (!skill.getJobs().contains(job)) {
            skill.getJobs().add(job);
            skillService.edit(skill);
        } else {
            throw new NotAllowedException("The job already has the skill");
        }

        return Response.ok().build();
    }

    @Path("/{id}/skill")
    @GET
    public Response getSkills(@PathParam("id") Long id) {

        return Optional.ofNullable(jobService.findInitializeSkills(id))
                .map(job -> Response.ok(job.getSkills()).build())
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
    public List<Job> getAllJobs(@QueryParam("skillId") Long skillId, @QueryParam("jobStatus") JobStatus status) {
        System.out.println(skillId);
        System.out.println(status);

        if (skillId != null) {
            return jobService.findBySkill(skillId);
        }

        if (status != null) {
            return jobService.findByStatus(status);
        }

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

    @GET
    @Path("generate-link/{job-id}/{employee-id}")
    //@Secured(Roles.EMPLOYEE)
    public Response generateLink(@PathParam("job-id") Long jobId, @PathParam("employee-id") Long employeeId) {

        Job job = jobService.find(jobId);
        if (job == null) {
            throw new NotFoundException("Job not found");
        }

        Employee employee = employeeSerivce.find(employeeId);
        if (employee == null) {
            throw new NotFoundException("Employee not found");
        }

        ReferHash referHash = referHashService.generateHash(job, employee);

        System.out.println(referHash);

        return Response.ok(referHash).build();
    }

}
