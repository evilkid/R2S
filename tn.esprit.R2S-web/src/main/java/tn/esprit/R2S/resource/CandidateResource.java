package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.*;
import tn.esprit.R2S.model.*;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Path("/api/candidate")
public class CandidateResource {


    @EJB
    private ICandidateService candidateService;

    @EJB
    private ICandidateFieldService candidateFieldService;

    @EJB
    private IJobService jobService;

    @EJB
    private ICertificationService certificationService;

    @EJB
    private ISkillService skillService;

    @EJB
    private ICandidateSkillService candidateSkillService;

    @GET
    public Response getAllCandidates(@QueryParam("skillId") Long skillId,
                                     @QueryParam("numDaysExpInf") Integer numDaysExpInf,
                                     @QueryParam("numDaysExpSup") Integer numDaysExpSup,
                                     @QueryParam("certification") String certificationName) {

        List<Candidate> result = null;

        if (skillId != null) {
            result = candidateService.findBySkillId(skillId);
        } else if (numDaysExpInf != null && numDaysExpSup != null) {
            result = candidateService.findByExperienceBetween(numDaysExpInf, numDaysExpSup);
        } else if (numDaysExpInf != null) {
            result = candidateService.findByExperience(numDaysExpInf);
        } else if (certificationName != null) {
            result = candidateService.findByCertification(certificationName);

        }

        return Response.ok(result).build();
    }

    @GET
    @Path("form")
    @Secured(Roles.CANDIDATE)
    public List<CandidateField> getCandidateField() {
        return candidateFieldService.findAll();
    }

    @PUT
    @Path("{candidate-cin}/{job-id}")
    public Response updateCandidateProgress(@PathParam("candidate-cin") Long candidateCin,
                                            @PathParam("job-id") Long jobId, CandidateJob candidateJob) {

        Candidate candidate = candidateService.findInitializeJobs(candidateCin);
        if (candidate == null) {
            throw new NotFoundException("Candidate not found");
        }

        Job job = jobService.find(jobId);
        if (job == null) {
            throw new NotFoundException("Job not found");
        }

        candidateJob.setJob(job);
        candidateJob.setCandidate(candidate);
        candidateJob.setDate(new Date());

        candidateJob.setCandidateJob(new CandidateJobPK(job.getId(), candidate.getCin()));

        if (candidate.getJobs().contains(candidateJob)) {
            int index = candidate.getJobs().indexOf(candidateJob);
            CandidateJob tmp = candidate.getJobs().get(index);

            tmp.setProgress(candidateJob.getProgress());
            tmp.setDate(candidateJob.getDate());

            candidate.getJobs().set(index, tmp);
        } else {

            candidate.getJobs().add(candidateJob);
        }

        candidateService.edit(candidate);

        return Response.ok().build();
    }

    @GET
    @Path("/exp/{duration}")
    public List<Candidate> getCandidateByExperience(@PathParam("duration") int duration) {
        return candidateService.findByExperience(duration);
    }

    @GET
    @Path("/exp/{duration1}/{duration2}")
    public List<Candidate> getCandidateByExperienceBetweenDurations(@PathParam("duration1") int duration1
            , @PathParam("duration2") int duration2) {
        return candidateService.findByExperienceBetween(duration1, duration2);
    }

    @GET
    @Path("/cer/{certificationId}")
    public List<Candidate> getCandidateByCertification(@PathParam("certificationId") long certificationId) {


        Certification certification = certificationService.find(certificationId);
        if (certification != null) {
            return candidateService.findByCertification(certification.getName());
        }

        return null;
    }

    @GET
    @Path("{id}/jobs")
    public Response getCandidateJobs(@PathParam("id") Long id) {
        Candidate candidate = candidateService.findInitializeJobs(id);

        return Optional.ofNullable(candidate).map(cand -> Response.ok(cand.getJobs()).build())
                .orElseThrow(NotFoundException::new);

    }

    @GET
    @Path("{id}/interviews")
    public Response getCandidateInterviews(@PathParam("id") Long id) {
        Candidate candidate = candidateService.findInitializeInterviews(id);

        return Optional.ofNullable(candidate).map(cand -> Response.ok(cand.getInterviews()).build())
                .orElseThrow(NotFoundException::new);

    }

    @GET
    @Path("{id}/certifications")
    public Response getCandidateCertifications(@PathParam("id") Long id) {
        Candidate candidate = candidateService.findInitializeCertifications(id);

        return Optional.ofNullable(candidate).map(cand -> Response.ok(cand.getCertifications()).build())
                .orElseThrow(NotFoundException::new);

    }

    @GET
    @Path("{id}/experiences")
    public Response getCandidateExperiences(@PathParam("id") Long id) {
        Candidate candidate = candidateService.findInitializeExperiences(id);

        return Optional.ofNullable(candidate).map(cand -> Response.ok(cand.getExperiences()).build())
                .orElseThrow(NotFoundException::new);

    }

    @GET
    @Path("{id}/skills")
    public Response getCandidateSkills(@PathParam("id") Long id) {
        Candidate candidate = candidateService.findInitializeSkills(id);

        return Optional.ofNullable(candidate).map(cand -> Response.ok(cand.getCandidateSkills()).build())
                .orElseThrow(NotFoundException::new);
    }

    @POST
    @Path("{id}/skills")
    public Response candidateAddSkill(@PathParam("id") Long id, CandidateSkill candidateSkill) {

        System.out.println(candidateSkill);

        candidateSkillService.edit(candidateSkill);

        return Response.ok().build();
    }

    @DELETE
    @Path("{id}/skills/{skill-id}")
    public Response candidateDeleteSkill(@PathParam("id") Long candidateId, @PathParam("skill-id") Long skillId) {

        Candidate candidate = candidateService.find(candidateId);
        Skill skill = skillService.find(skillId);

        if (candidate == null) {
            throw new NotFoundException("Candidate not found");
        }

        if (skill == null) {
            throw new NotFoundException("Not not found");
        }

        candidateSkillService.remove(new CandidateSkill(new CandidateSkillPK(candidateId, skillId)));

        return Response.ok().build();
    }

    @POST
    public Response registerCandidate(ReferHash referHash, Candidate candidate) throws URISyntaxException {

        candidate.setReferee(referHash.getEmployee());
        candidateService.create(candidate);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/candidate/" + candidate.getCin())),
                "candidateQuizModel", candidate.getCin().toString())
                .entity(candidate).build();
    }
}
