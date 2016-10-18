package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Job;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IJobService {
    void create(Job entity);

    Job edit(Job entity);

    void remove(Job entity);

    Job find(Object id);

    List<Job> findAll();
}
