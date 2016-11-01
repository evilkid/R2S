package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateFieldService;
import tn.esprit.R2S.interfaces.ICandidateService;
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

    @GET
    public List<Candidate> getAllCandidates(@QueryParam("skillId") int skillId) {
        return candidateService.findBySkillId(skillId);
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
}
