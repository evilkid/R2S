package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.model.JobFieldValue;
import tn.esprit.R2S.util.enums.JobStatus;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by EvilKids on 10/18/2016.
 */
@Remote
public interface IJobService {
    void create(Job job);

    Job edit(Job job);

    void remove(Job job);

    Job find(Object id);

    Job findInitializeRewards(Object id);

    Job findInitializeSkills(Object id);

    List<Job> findAll();

    List<Job> findByStatus(JobStatus jobStatus);

    List<Job> findBySkill(Long skillId);

    List<JobFieldValue> getJobFieldValue(Long jobId);

    List<Candidate> findCandidates(Long jobId);
}
