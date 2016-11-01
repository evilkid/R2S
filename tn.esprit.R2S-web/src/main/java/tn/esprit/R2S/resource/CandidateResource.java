package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateFieldService;
import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.interfaces.ICertificationService;
import tn.esprit.R2S.interfaces.IJobService;
import tn.esprit.R2S.model.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

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

    @GET
    public Response getAllCandidates(@QueryParam("skillId") Integer skillId,
                                     @QueryParam("numDaysExpInf") Integer numDaysExpInf,
                                     @QueryParam("numDaysExpSup") Integer numDaysExpSup) {

        List<Candidate> result = null;

        if (skillId != null) {
            result = candidateService.findBySkillId(skillId);
        }

        if (numDaysExpInf != null && numDaysExpSup != null) {
            result = candidateService.findByExperienceBetween(numDaysExpInf, numDaysExpSup);
        }

        if (numDaysExpInf != null) {
            result = candidateService.findByExperience(numDaysExpInf);
        }

        return Response.ok(result).build();
    }

    @GET
    @Path("form")
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
    public List<Candidate> getCandidateByExperienceBetweenDurations(@PathParam("certificationId") long certificationId) {


        Certification certification = certificationService.find(certificationId);
        if (certification != null) {
            return candidateService.findByCertification(certification);
        }

        return null;
    }
}
