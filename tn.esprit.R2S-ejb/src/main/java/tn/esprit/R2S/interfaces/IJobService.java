package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.util.enums.JobStatus;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IJobService {
    void create(Job job);

    Job edit(Job job);

    void remove(Job job);

    Job find(Object id);

    List<Job> findAll();

    List<Job> findByStatus(JobStatus jobStatus);
}
